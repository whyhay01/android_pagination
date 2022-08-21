package com.example.androidpagination.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.androidpagination.data.model.Character
import com.example.androidpagination.data.model.RickAndMortyResponse
import com.example.androidpagination.network.RickAndMortyService
import com.example.androidpagination.utils.START_PAGE

class RickAndMortyDataSource(
    private val apiService: RickAndMortyService
    ): PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val position = if (params.key == null) START_PAGE else params.key

        try {
            val response: RickAndMortyResponse = apiService.getCharacter()

            val character: List<Character> = response.characters

            if (character.isNotEmpty()){

                val result =LoadResult.Page(
                    data = character,
                    prevKey = if (position == START_PAGE) null else position!!-1,
                    nextKey = position+1
                )
            }

        }catch (e:Exception){

        }

    }
}