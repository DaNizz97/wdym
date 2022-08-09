package xyz.danizz.wdym.data.repository

import org.springframework.data.mongodb.repository.MongoRepository
import xyz.danizz.wdym.data.entity.MemeImagePath

interface MemeImagePathRepository : MongoRepository<MemeImagePath, String> {
    override fun deleteAll()
    override fun <S : MemeImagePath> save(entity: S): S
}