package com.example.task_microservice.data;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.firestore.annotation.ServerTimestamp;
import lombok.Data;

@Data
public class Task {

    @DocumentId
    public String id;
    public String title;
    public String description;
    public boolean completed;

    @ServerTimestamp
    public Timestamp createdAt;

    @ServerTimestamp
    public Timestamp updatedAt;     // Last updated timestamp

}
