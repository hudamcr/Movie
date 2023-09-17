package com.huda.movie.data.mapper

import com.huda.movie.data.model.*
import com.huda.movie.presentation.utils.orFalse
import com.huda.movie.presentation.utils.orZero

fun MovieDetailsResponse.toMovieDetails(): MovieDetails {
    return MovieDetails(
        id = id.orZero(),
        adult = adult.orFalse(),
        backdropPath = backdropPath.orEmpty(),
        belongsToCollection = belongsToCollection ?: "",
        budget = budget.orZero(),
        genres = genres?.map { it.toGenreList() } ?: emptyList(),
        homepage = homepage.orEmpty(),
        imdbId = imdbId.orEmpty(),
        originalLanguage = originalLanguage.orEmpty(),
        originalTitle = originalTitle.orEmpty(),
        overview = overview.orEmpty(),
        popularity = popularity.orZero(),
        posterPath = posterPath.orEmpty(),
        productionCompanies = productionCompanies?.map { it.toProductionCompany() } ?: emptyList(),
        productionCountries = productionCountries?.map { it.toProductionCounty() } ?: emptyList(),
        releaseDate = releaseDate.orEmpty(),
        revenue = revenue.orZero(),
        runtime = runtime.orZero(),
        spokenLanguages = spokenLanguages?.map { it.toSpokenLanguage() } ?: emptyList(),
        status = status.orEmpty(),
        tagline = tagline.orEmpty(),
        title = title.orEmpty(),
        video = video.orFalse(),
        voteAverage = voteAverage.orZero(),
        voteCount = voteCount.orZero()
    )
}

fun ProductionCompanyResponse.toProductionCompany(): ProductionCompany {
    return ProductionCompany(
        id = id.orZero(),
        logoPath = logoPath.orEmpty(),
        name = name.orEmpty(),
        originCountry = originCountry.orEmpty()
    )
}

fun ProductionCountryResponse.toProductionCounty(): ProductionCountry {
    return ProductionCountry(
        iso3166_1 = iso3166_1.orEmpty(),
        name = name.orEmpty()
    )
}

fun SpokenLanguageResponse.toSpokenLanguage(): SpokenLanguage {
    return SpokenLanguage(
        englishName = englishName.orEmpty(),
        iso639_1 = iso639_1.orEmpty(),
        name = name.orEmpty()
    )
}


