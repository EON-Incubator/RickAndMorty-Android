package com.example.rickandmorty.domain.episodes

import app.moviebase.tmdb.model.TmdbVideoSite
import app.moviebase.tmdb.model.TmdbVideoType
import com.example.rickandmorty.R
import com.google.gson.annotations.SerializedName

data class TmdbEpisodeDetail(
    val overview: String = "",
    @SerializedName(R.string.vote_average.toString()) val voteAverage: Float? = null,
    val images: EpisodeImages? = null,
    val videos: EpisodeVideos? = null,
)

data class EpisodeImages(
    var stills: List<ImageData>,
)

data class EpisodeVideos(
    var results: List<VideoData>,
)

data class ImageData(
    @SerializedName(R.string.file_path.toString()) val filePath: String,
    @SerializedName(R.string.aspect_ratio.toString()) val aspectRation: Float,
    @SerializedName(R.string.height.toString()) val height: Int,
    @SerializedName(R.string.width.toString()) val width: Int,
    @SerializedName(R.string.iso_639_1.toString()) val iso639: String? = null,
    @SerializedName(R.string.vote_average.toString()) val voteAverage: Float? = null,
    @SerializedName(R.string.vote_count.toString()) val voteCount: Int? = null,
)

data class VideoData(
    @SerializedName(R.string.id.toString()) val id: String,
    @SerializedName(R.string.iso_639_1.toString()) val iso639: String? = null,
    @SerializedName(R.string.iso_3166_1.toString()) val iso3166: String? = null,
    @SerializedName(R.string.key.toString()) val key: String? = null,
    @SerializedName(R.string.site.toString()) val site: TmdbVideoSite? = null,
    @SerializedName(R.string.name.toString()) val name: String? = null,
    @SerializedName(R.string.size.toString()) val size: Int? = null, // 360, 480, 720, 1080
    @SerializedName(R.string.type_all_small_case.toString()) val type: TmdbVideoType? = null,
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