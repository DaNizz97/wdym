package xyz.danizz.wdym.data.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class MemeImagePath(
    @Id
    val id: ObjectId = ObjectId.get(),
    val path: String
)
