package com.example.pkemonapipokedex.data.mapper

import com.example.pkemonapipokedex.data.response.PagesPokemonResponse
import com.example.pkemonapipokedex.domain.model.InformationPages

class PageMapperImpl: DomainMapper<PagesPokemonResponse, InformationPages> {
    override fun toDomain(from: PagesPokemonResponse): InformationPages {
        return InformationPages(
            next = from.next,
            previous = from.previous,
        )
    }
}