package com.example.rickandmorty.domain.episodes

import app.moviebase.tmdb.model.TmdbVideoSite
import app.moviebase.tmdb.model.TmdbVideoType
import com.google.gson.annotations.SerializedName

data class TmdbEpisodeDetail(
    val overview: String = "",
    @SerializedName("vote_average") val voteAverage: Float? = null,
    val images: EpisodeImages? = null,
)

data class EpisodeImages(
    var stills: List<ImageData>,
)

data class EpisodeVideos(
    var results: List<VideoData>,
)

data class ImageData(
    @SerializedName("file_path") val filePath: String,
    @SerializedName("aspect_ratio") val aspectRation: Float,
    @SerializedName("height") val height: Int,
    @SerializedName("width") val width: Int,
    @SerializedName("iso_639_1") val iso639: String? = null,
    @SerializedName("vote_average") val voteAverage: Float? = null,
    @SerializedName("vote_count") val voteCount: Int? = null,
)

data class VideoData(
    @SerializedName("id") val id: String,
    @SerializedName("iso_639_1") val iso639: String? = null,
    @SerializedName("iso_3166_1") val iso3166: String? = null,
    @SerializedName("key") val key: String? = null,
    @SerializedName("site") val site: TmdbVideoSite? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("size") val size: Int? = null, // 360, 480, 720, 1080
    @SerializedName("type") val type: TmdbVideoType? = null,
)

// @Serializable
// data class TmdbEpisodeDetail(
//    @SerialName("id") override val id: Int,
//    @SerialName("overview") val overview: String,
//    @SerialName("episode_number") val episodeNumber: Int,
//    @SerialName("season_number") val seasonNumber: Int,
//    @SerialName("air_date")
//    @Serializable(LocalDateSerializer::class)
//    val airDate: LocalDate? = null,
//    @SerialName("name") val name: String? = null,
//    @SerialName("vote_average") override val voteAverage: Float? = null,
//    @SerialName("vote_count") override val voteCount: Int? = null,
//    @SerialName("still_path") val stillPath: String? = null,
//    @SerialName("images") val images: TmdbResult<TmdbImages>? = null,
//    @SerialName("crew") val crew: List<TmdbCrew>? = null,
//    @SerialName("guest_stars") val guestStars: List<TmdbCast>? = null,
//    @SerialName("external_ids") val externalIds: TmdbExternalIds? = null
// ) : TmdbAnyMedia, TmdbBackdropMedia, TmdbRatingItem {
//    override val backdropPath: String? get() = stillPath
// }