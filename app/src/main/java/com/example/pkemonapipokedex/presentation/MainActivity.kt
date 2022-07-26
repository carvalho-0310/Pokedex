package com.example.pkemonapipokedex.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatCheckedTextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.pkemonapipokedex.R
import com.example.pkemonapipokedex.domain.model.InformationPokemon
import com.example.pkemonapipokedex.domain.model.TypePossible
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var imagePokemon: AppCompatImageView
    private lateinit var idPokemon: AppCompatCheckedTextView
    private lateinit var namePokemon: AppCompatCheckedTextView

    private lateinit var separatorOne: View
    private lateinit var separatorTwo: View
    private lateinit var separatorTree: View

    private lateinit var backgroundColor: ConstraintLayout

    private lateinit var typeOne: CardView
    private lateinit var textOne: AppCompatCheckedTextView

    private lateinit var typeTwo: CardView
    private lateinit var textTwo: AppCompatCheckedTextView

    private lateinit var typeTree: CardView
    private lateinit var textTree: AppCompatCheckedTextView

    private lateinit var typeFour: CardView
    private lateinit var textFour: AppCompatCheckedTextView

    private val viewModel: PokemonViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imagePokemon = findViewById(R.id.image_pokemon)
        idPokemon = findViewById(R.id.id_pokemon)
        namePokemon = findViewById(R.id.name_pokemon)

        separatorOne = findViewById(R.id.v_0ne)
        separatorTwo = findViewById(R.id.v_two)
        separatorTree = findViewById(R.id.v_tree)

        backgroundColor = findViewById(R.id.background_color)

        typeOne = findViewById(R.id.type_1)
        textOne = findViewById(R.id.text_1)
        typeOne.visibility = 8

        typeTwo = findViewById(R.id.type_2)
        textTwo = findViewById(R.id.text_2)
        typeTwo.visibility = 8

        typeTree = findViewById(R.id.type_3)
        textTree = findViewById(R.id.text_3)
        typeTree.visibility = 8

        typeFour = findViewById(R.id.type_4)
        textFour = findViewById(R.id.text_4)
        typeFour.visibility = 8

        viewModel.getPokemonName()

        viewModel.pLiveData.observe(this) {
            setPokemon(it)
        }
    }

    private fun setPokemon(pokemon: InformationPokemon) {
        Glide.with(imagePokemon)
            .load(pokemon.sprites)
            .into(imagePokemon)
        idPokemon.text =  getString(R.string.id_pokemon,pokemon.id.toString())
        namePokemon.text = getString(R.string.name_pokemon,pokemon.name)

        hasTypeOne(pokemon)
        hasTypeTwo(pokemon)
        hasTypeTree(pokemon)
        hasTypeFour(pokemon)
    }

    private fun hasTypeOne(pokemon: InformationPokemon) {
        if (viewModel.hasIsType(pokemon, 0)) {
            typeOne.visibility = 0
            textOne.text = pokemon.types[0].name
            setBackgroundColorType(typeOne, pokemon.types[0])
            setBackgroundColor(pokemon.types[0])
        }
    }

    private fun hasTypeTwo(pokemon: InformationPokemon) {
        if (viewModel.hasIsType(pokemon, 1)) {
            typeTwo.visibility = 0
            textTwo.text = pokemon.types[1].name
            setBackgroundColorType(typeTwo, pokemon.types[1])
        }
    }

    private fun hasTypeTree(pokemon: InformationPokemon) {
        if (viewModel.hasIsType(pokemon, 2)) {
            typeTree.visibility = 0
            textTree.text = pokemon.types[2].name
            setBackgroundColorType(typeTree, pokemon.types[2])
        }
    }

    private fun hasTypeFour(pokemon: InformationPokemon) {
        if (viewModel.hasIsType(pokemon, 3)) {
            typeFour.visibility = 0
            textFour.text = pokemon.types[3].name
            setBackgroundColorType(typeFour, pokemon.types[3])
        }
    }

    private fun setBackgroundColorType(card: CardView, type: TypePossible) {
        when (type) {
            TypePossible.BUG -> card.setCardBackgroundColor(resources.getColor(R.color.bug))
            TypePossible.DARK -> card.setCardBackgroundColor(resources.getColor(R.color.dark))
            TypePossible.DRAGON -> card.setCardBackgroundColor(resources.getColor(R.color.dragon))
            TypePossible.ELECTRIC -> card.setCardBackgroundColor(resources.getColor(R.color.electric))
            TypePossible.FAIRY -> card.setCardBackgroundColor(resources.getColor(R.color.fairy))
            TypePossible.FIGHTING -> card.setCardBackgroundColor(resources.getColor(R.color.fighting))
            TypePossible.FIRE -> card.setCardBackgroundColor(resources.getColor(R.color.fire))
            TypePossible.FLYING -> card.setCardBackgroundColor(resources.getColor(R.color.flying))
            TypePossible.GHOST -> card.setCardBackgroundColor(resources.getColor(R.color.ghost))
            TypePossible.GRASS -> card.setCardBackgroundColor(resources.getColor(R.color.grass))
            TypePossible.GROUND -> card.setCardBackgroundColor(resources.getColor(R.color.ground))
            TypePossible.ICE -> card.setCardBackgroundColor(resources.getColor(R.color.ice))
            TypePossible.NORMAL -> card.setCardBackgroundColor(resources.getColor(R.color.normal))
            TypePossible.POISON -> card.setCardBackgroundColor(resources.getColor(R.color.poison))
            TypePossible.PSYCHIC -> card.setCardBackgroundColor(resources.getColor(R.color.psychic))
            TypePossible.ROCK -> card.setCardBackgroundColor(resources.getColor(R.color.rock))
            TypePossible.STEEL -> card.setCardBackgroundColor(resources.getColor(R.color.steel))
            TypePossible.WATER -> card.setCardBackgroundColor(resources.getColor(R.color.water))
        }
    }

    private fun setBackgroundColor(type: TypePossible) {
        when (type) {
            TypePossible.BUG -> {
                backgroundColor.setBackgroundColor(resources.getColor(R.color.bugOptional))
                separatorOne.setBackgroundColor(resources.getColor(R.color.bug))
                separatorTwo.setBackgroundColor(resources.getColor(R.color.bug))
                separatorTree.setBackgroundColor(resources.getColor(R.color.bug))
            }
            TypePossible.DARK -> {
                backgroundColor.setBackgroundColor(resources.getColor(R.color.darkOptional))
                separatorOne.setBackgroundColor(resources.getColor(R.color.dark))
                separatorTwo.setBackgroundColor(resources.getColor(R.color.dark))
                separatorTree.setBackgroundColor(resources.getColor(R.color.dark))
            }
            TypePossible.DRAGON -> {
                backgroundColor.setBackgroundColor(resources.getColor(R.color.dragonOptional))
                separatorOne.setBackgroundColor(resources.getColor(R.color.dragon))
                separatorTwo.setBackgroundColor(resources.getColor(R.color.dragon))
                separatorTree.setBackgroundColor(resources.getColor(R.color.dragon))
            }
            TypePossible.ELECTRIC -> {
                backgroundColor.setBackgroundColor(resources.getColor(R.color.electricOptional))
                separatorOne.setBackgroundColor(resources.getColor(R.color.electric))
                separatorTwo.setBackgroundColor(resources.getColor(R.color.electric))
                separatorTree.setBackgroundColor(resources.getColor(R.color.electric))
            }
            TypePossible.FAIRY -> {
                backgroundColor.setBackgroundColor(resources.getColor(R.color.fairyOptional))
                separatorOne.setBackgroundColor(resources.getColor(R.color.fairy))
                separatorTwo.setBackgroundColor(resources.getColor(R.color.fairy))
                separatorTree.setBackgroundColor(resources.getColor(R.color.fairy))
            }
            TypePossible.FIGHTING -> {
                backgroundColor.setBackgroundColor(resources.getColor(R.color.fightingOptional))
                separatorOne.setBackgroundColor(resources.getColor(R.color.fighting))
                separatorTwo.setBackgroundColor(resources.getColor(R.color.fighting))
                separatorTree.setBackgroundColor(resources.getColor(R.color.fighting))
            }
            TypePossible.FIRE -> {
                backgroundColor.setBackgroundColor(resources.getColor(R.color.fireOptional))
                separatorOne.setBackgroundColor(resources.getColor(R.color.fire))
                separatorTwo.setBackgroundColor(resources.getColor(R.color.fire))
                separatorTree.setBackgroundColor(resources.getColor(R.color.fire))
            }
            TypePossible.FLYING -> {
                backgroundColor.setBackgroundColor(resources.getColor(R.color.flyingOptional))
                separatorOne.setBackgroundColor(resources.getColor(R.color.flying))
                separatorTwo.setBackgroundColor(resources.getColor(R.color.flying))
                separatorTree.setBackgroundColor(resources.getColor(R.color.flying))
            }
            TypePossible.GHOST -> {
                backgroundColor.setBackgroundColor(resources.getColor(R.color.ghostOptional))
                separatorOne.setBackgroundColor(resources.getColor(R.color.ghost))
                separatorTwo.setBackgroundColor(resources.getColor(R.color.ghost))
                separatorTree.setBackgroundColor(resources.getColor(R.color.ghost))
            }
            TypePossible.GRASS -> {
                backgroundColor.setBackgroundColor(resources.getColor(R.color.grassOptional))
                separatorOne.setBackgroundColor(resources.getColor(R.color.grass))
                separatorTwo.setBackgroundColor(resources.getColor(R.color.grass))
                separatorTree.setBackgroundColor(resources.getColor(R.color.grass))
            }
            TypePossible.GROUND -> {
                backgroundColor.setBackgroundColor(resources.getColor(R.color.groundOptional))
                separatorOne.setBackgroundColor(resources.getColor(R.color.ground))
                separatorTwo.setBackgroundColor(resources.getColor(R.color.ground))
                separatorTree.setBackgroundColor(resources.getColor(R.color.ground))
            }
            TypePossible.ICE -> {
                backgroundColor.setBackgroundColor(resources.getColor(R.color.iceOptional))
                separatorOne.setBackgroundColor(resources.getColor(R.color.ice))
                separatorTwo.setBackgroundColor(resources.getColor(R.color.ice))
                separatorTree.setBackgroundColor(resources.getColor(R.color.ice))
            }
            TypePossible.NORMAL -> {
                backgroundColor.setBackgroundColor(resources.getColor(R.color.normalOptional))
                separatorOne.setBackgroundColor(resources.getColor(R.color.normal))
                separatorTwo.setBackgroundColor(resources.getColor(R.color.normal))
                separatorTree.setBackgroundColor(resources.getColor(R.color.normal))
            }
            TypePossible.POISON -> {
                backgroundColor.setBackgroundColor(resources.getColor(R.color.poisonOptional))
                separatorOne.setBackgroundColor(resources.getColor(R.color.poison))
                separatorTwo.setBackgroundColor(resources.getColor(R.color.poison))
                separatorTree.setBackgroundColor(resources.getColor(R.color.poison))
            }
            TypePossible.PSYCHIC -> {
                backgroundColor.setBackgroundColor(resources.getColor(R.color.psychicOptional))
                separatorOne.setBackgroundColor(resources.getColor(R.color.psychic))
                separatorTwo.setBackgroundColor(resources.getColor(R.color.psychic))
                separatorTree.setBackgroundColor(resources.getColor(R.color.psychic))
            }
            TypePossible.ROCK -> {
                backgroundColor.setBackgroundColor(resources.getColor(R.color.rockOptional))
                separatorOne.setBackgroundColor(resources.getColor(R.color.rock))
                separatorTwo.setBackgroundColor(resources.getColor(R.color.rock))
                separatorTree.setBackgroundColor(resources.getColor(R.color.rock))
            }
            TypePossible.STEEL -> {
                backgroundColor.setBackgroundColor(resources.getColor(R.color.steelOptional))
                separatorOne.setBackgroundColor(resources.getColor(R.color.steel))
                separatorTwo.setBackgroundColor(resources.getColor(R.color.steel))
                separatorTree.setBackgroundColor(resources.getColor(R.color.steel))
            }
            TypePossible.WATER -> {
                backgroundColor.setBackgroundColor(resources.getColor(R.color.waterOptional))
                separatorOne.setBackgroundColor(resources.getColor(R.color.water))
                separatorTwo.setBackgroundColor(resources.getColor(R.color.water))
                separatorTree.setBackgroundColor(resources.getColor(R.color.water))
            }
        }
    }
}
