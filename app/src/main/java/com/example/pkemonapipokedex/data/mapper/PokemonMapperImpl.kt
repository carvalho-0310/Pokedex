package com.example.pkemonapipokedex.data.mapper

import android.util.Log
import com.example.pkemonapipokedex.data.response.InformationPokemonResponse
import com.example.pkemonapipokedex.data.response.Types
import com.example.pkemonapipokedex.domain.model.InformationPokemon
import com.example.pkemonapipokedex.domain.model.TypePossible

class PokemonMapperImpl : DomainMapper<InformationPokemonResponse, InformationPokemon> {

    override fun toDomain(from: InformationPokemonResponse): InformationPokemon {
        return InformationPokemon(
            id = from.id,
            name = from.name,
            height = from.height,
            weight = from.weight,
            sprites = from.sprites.other.officialArtwork.imageUrl,
            types = typesNames(from.types)
        )
    }

    private fun typesNames(types: List<Types>): List<TypePossible>
    {
        val result = mutableListOf<TypePossible>()

            types.forEach { types ->
                when(types.typeName.nameType.uppercase()){
                    TypePossible.BUG.name -> result.add(TypePossible.BUG)
                    TypePossible.DARK.name-> result.add(TypePossible.DARK)
                    TypePossible.DRAGON.name -> result.add(TypePossible.DRAGON)
                    TypePossible.ELECTRIC.name -> result.add(TypePossible.ELECTRIC)
                    TypePossible.FAIRY.name -> result.add(TypePossible.FAIRY)
                    TypePossible.FIGHTING.name -> result.add(TypePossible.FIGHTING)
                    TypePossible.FIRE.name -> result.add(TypePossible.FIRE)
                    TypePossible.FLYING.name -> result.add(TypePossible.FLYING)
                    TypePossible.GHOST.name -> result.add(TypePossible.GHOST)
                    TypePossible.GRASS.name -> result.add(TypePossible.GRASS)
                    TypePossible.GROUND.name -> result.add(TypePossible.GROUND)
                    TypePossible.ICE.name -> result.add(TypePossible.ICE)
                    TypePossible.NORMAL.name -> result.add(TypePossible.NORMAL)
                    TypePossible.POISON.name -> result.add(TypePossible.POISON)
                    TypePossible.PSYCHIC.name -> result.add(TypePossible.PSYCHIC)
                    TypePossible.ROCK.name -> result.add(TypePossible.ROCK)
                    TypePossible.STEEL.name -> result.add(TypePossible.STEEL)
                    TypePossible.WATER.name -> result.add(TypePossible.WATER)
            }
        }
        result.forEach {
            Log.d("maaaaa",it.name)
        }

        return result
    }
}