package com.example.rickandmorty.data.local

// import androidx.room.*
// import kotlinx.coroutines.flow.Flow
// import com.example.rickandmorty.data.local.schema.Character

// @Dao
// interface CharacterDao {
//
//    @Query("SELECT * from characters ORDER BY name ASC")
//    fun getAllCharacters(): Flow<List<Character>>
//
//    @Query("SELECT * from characters WHERE id = :id")
//    fun getCharacter(id: Int): Flow<Character>
//
//    // Specify the conflict strategy as IGNORE, when the user tries to add an
//    // existing Item into the database Room ignores the conflict.
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insert(character: Character)
//
//    @Update
//    suspend fun update(character: Character)
//
//    @Delete
//    suspend fun delete(character: Character)
// }