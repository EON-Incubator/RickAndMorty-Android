package com.example.rickandmorty.domain.episodeusecase.data.repository

import com.example.rickandmorty.domain.DetailedEpisode
import com.example.rickandmorty.domain.Episodes

object FakeDataSource {

    const val imgOne = "url.one"
    const val imgTwo = "url.two"
    const val imgThree = "url.Three"
    const val imgFour = "url.Four"
    const val nameOne = "nameOne"
    const val nameTwo = "nameTwo"
    const val firstPropertyOne = "propertyOne"
    const val firstPropertyTwo = "propertyTwo"
    const val secondPropertyOne = "propertyOne"
    const val secondPropertyTwo = "propertyTwo"
    const val idOne = "idOne"
    const val idTwo = "idTwo"

    val imageList = listOf(
        imgOne,
        imgTwo,
        imgThree,
        imgFour
    )

    val characterList = listOf(
        imgOne,
        imgTwo,
        imgThree,
        imgFour
    )

    val episodesList = listOf(
        Episodes(
            id = idOne,
            name = nameOne,
            episode = firstPropertyOne,
            air_date = secondPropertyOne,
            images = imageList
        ),
        Episodes(
            id = idTwo,
            name = nameTwo,
            episode = firstPropertyTwo,
            air_date = secondPropertyTwo,
            images = imageList
        )
    )


//    val episodeDetail = listOf(
//        DetailedEpisode(
//            id = idOne,
//            name = nameOne,
//            episode = firstPropertyOne,
//            air_date = secondPropertyOne,
//            characters = characterList
//        ),
//        DetailedEpisode(
//            id = idTwo,
//            name = nameTwo,
//            episode = firstPropertyTwo,
//            air_date = secondPropertyTwo,
//            characters = characterList
//        )
//    )

}