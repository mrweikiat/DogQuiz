package com.example.dogquiz.utilities.parsing

object DogQuizParserUtils {
    fun getBreedFromUrl(url: String): String {
        return url.split("/breeds/")[1].split("/")[0].replace('-', ' ')
    }
}
