package com.example.pkemonapipokedex.data.mapper

import com.example.pkemonapipokedex.data.response.PagesPokemonResponse
import com.example.pkemonapipokedex.data.response.PokemonResponse
import com.example.pkemonapipokedex.domain.model.InformationPages

class PageMapperImpl : DomainMapper<PagesPokemonResponse, InformationPages> {
    override fun toDomain(from: PagesPokemonResponse): InformationPages {
        return InformationPages(
            count = from.count,
            names = mapperList(from.results)
        )
    }
    private fun mapperList(list :List<PokemonResponse>): List<String>{
        val result = mutableListOf<String>()
        list.forEach {
            result.add(it.name)
        }
        return result
    }
}