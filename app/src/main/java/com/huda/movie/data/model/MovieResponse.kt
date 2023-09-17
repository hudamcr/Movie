import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val page: Int?,
    val results: List<MovieListResponse>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)

data class MovieListResponse(
    val id: Int?,
    @SerializedName("original_title")
    val originalTitle: String?,
    val title: String?,
    val overview: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val popularity: Double?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    val voteCount: Int?
)