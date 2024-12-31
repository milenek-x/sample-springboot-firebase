const apiUrl = 'http://localhost:8070/api/v1/tasks';

document.getElementById('task-form').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const title = document.getElementById('title').value;
    const description = document.getElementById('description').value;
    const completed = document.getElementById('completed').value === 'true';
    const createdAt = null;
    const updatedAt = null;

    const response = await fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ title, description, completed, createdAt, updatedAt })
    });

    if (response.ok) {
        loadTasks(); // Reload tasks after adding
        resetFields(); // Reset input fields
    } else {
        alert('Error adding task');
    }
});

async function loadTasks() {
    const response = await fetch(apiUrl);
    
    if (response.ok) {
        const tasks = await response.json();
        
        const tasksTableBody = document.getElementById('tasks');
        tasksTableBody.innerHTML = ''; // Clear existing tasks
        
        tasks.forEach(task => {
            const row = document.createElement('tr');
            
            row.innerHTML = `
                <td>${task.id}</td>
                <td><input type='text' value='${task.title}' disabled class='editable title' data-id='${task.id}' /></td>
                <td><input type='text' value='${task.description}' disabled class='editable description' data-id='${task.id}' /></td>
                <td>
                    <select class='editable completed' data-id='${task.id}' disabled>
                        <option value="false" ${task.completed ? '' : 'selected'}>No</option>
                        <option value="true" ${task.completed ? 'selected' : ''}>Yes</option>
                    </select>
                </td>
                <td>${new Date(task.createdAt.seconds * 1000).toLocaleString()}</td> <!-- Convert seconds to date -->
                <td>${new Date(task.updatedAt.seconds * 1000).toLocaleString()}</td> <!-- Convert seconds to date -->
                <td>
                    <button onclick='enableEdit("${task.id}")'>Update</button> 
                    <button onclick='deleteTask("${task.id}")'>Delete</button>
                </td>
            `;
            
            tasksTableBody.appendChild(row);
        });
    } else {
        console.error('Error fetching tasks');
    }
}

function enableEdit(id) {
   const row = document.querySelector(`tr td input[data-id="${id}"]`).closest('tr'); // Get the whole row
   const inputs = row.querySelectorAll('.editable'); // Get all editable fields in this row

   inputs.forEach(input => {
       input.disabled = false; // Enable editing for all inputs
   });

   // Change button text to Save
   const updateButton = row.querySelector("button");
   updateButton.textContent = "Save";
   updateButton.setAttribute("onclick", `saveTask("${id}")`); // Change function to save
}

async function saveTask(id) {
   const titleInput = document.querySelector(`tr td input[data-id="${id}"].title`);
   const descriptionInput = document.querySelector(`tr td input[data-id="${id}"].description`);
   const completedSelect = document.querySelector(`tr td select[data-id="${id}"].completed`);

   if (!titleInput || !descriptionInput || !completedSelect) {
       console.error('One of the inputs is not found.');
       return;
   }

   const title = titleInput.value;
   const description = descriptionInput.value;
   const completed = completedSelect.value === 'true'; // Get new completed status

   // Send PUT request to update task
   const response = await fetch(`${apiUrl}/${id}`, {
       method: 'PUT',
       headers: {
           'Content-Type': 'application/json'
       },
       body: JSON.stringify({ title, description, completed })
   });

   if (response.ok) {
       alert('Task updated successfully!');
       loadTasks(); // Reload tasks after updating
   } else {
       alert('Error updating task');
   }
}

async function deleteTask(id) {
   const response = await fetch(`${apiUrl}/${id}`, {
       method: 'DELETE'
   });

   if (response.ok) {
       alert('Task deleted successfully!');
       loadTasks(); // Reload tasks after deleting
   } else {
       alert('Error deleting task');
   }
}

function resetFields() {
   document.getElementById('title').value = '';
   document.getElementById('description').value = '';
   document.getElementById('completed').value = 'false'; // Reset to default "No"
}

function showPopup(taskId) {
   const popup = document.getElementById("popup");
   popup.classList.remove("hidden");
   document.getElementById("task-id").textContent = taskId; // Show the created task ID
}

function closePopup() {
   const popup = document.getElementById("popup");
   popup.classList.add("hidden");
}

// Load tasks on initial page load
loadTasks();
