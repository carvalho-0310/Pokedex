package com.example.pkemonapipokedex.data.mapper

import com.example.pkemonapipokedex.data.response.EffectText
import com.example.pkemonapipokedex.data.response.InformationMoveResponse
import com.example.pkemonapipokedex.domain.model.InformationMove

class MovesMapperImpl : DomainMapper<InformationMoveResponse, InformationMove> {

    override fun toDomain(from: InformationMoveResponse): InformationMove {
        return InformationMove(
            effect = getEffect(from.effect),
            name = from.name?: "pode ser nuill",
            power = from.power?: 0,
            pp = from.pp?: 0
        )
    }
    private fun getEffect(item:List<EffectText>? ): String{
        if(item == null)return "we have no description"
       return item[0].effect.toString()
    }
}
