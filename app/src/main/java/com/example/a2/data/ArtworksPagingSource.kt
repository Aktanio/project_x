package com.example.a2.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException

class ArtworksPagingSource(
    private val artworksAPI: ArtworksAPI
): PagingSource<Int, ArtworksResponse.Artwork>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArtworksResponse.Artwork> {
        return try {
            val currentPage = params.key ?: 1
            val response = artworksAPI.getAllArtworks(currentPage)

            LoadResult.Page(
                data = response.data,
                prevKey = if (currentPage == 1) null else currentPage -1,
                nextKey = if (response.data.isEmpty()) null else currentPage +1
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        } catch (e: HttpException){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArtworksResponse.Artwork>): Int? {
        return state.anchorPosition?.let { anchorPosition->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}