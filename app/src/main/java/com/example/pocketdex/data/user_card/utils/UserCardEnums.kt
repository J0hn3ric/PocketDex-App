package com.example.pocketdex.data.user_card.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.pocketdex.R

enum class Rarity(val rarityText: String) {
    ONE_DIA(rarityText = "1-Dia"),
    TWO_DIA(rarityText = "2-Dia"),
    THREE_DIA(rarityText = "3-Dia"),
    FOUR_DIA(rarityText = "4-Dia"),
    ONE_STAR(rarityText = "1-Star"),
    TWO_STAR(rarityText = "2-Star"),
    THREE_STAR(rarityText = "3-Star"),
    ONE_SHINY(rarityText = "1-Shiny"),
    TWO_SHINY(rarityText = "2-Shiny"),
    CROWN(rarityText = "Crown")
}

enum class Expansion(
    val expansionId: String,
    @StringRes val expansionName: Int,
    @DrawableRes val imgRes: Int = R.drawable.baseline_person_24
) {
    GENETIC_APEX(
        expansionId = "A1",
        expansionName = R.string.expansion_A1,
        imgRes = R.drawable.geneticapex
    ),
    MYTHICAL_ISLAND(
        expansionId = "A1a",
        expansionName = R.string.expansion_A1a,
        imgRes = R.drawable.mythicalisland
    ),
    SPACE_TIME_SMACKDOWN(
        expansionId = "A2",
        expansionName = R.string.expansion_A2,
        imgRes = R.drawable.space_timesmackdown
    ),
    TRIUMPHANT_LIGHT(
        expansionId = "A2a",
        expansionName = R.string.expansion_A2a,
        imgRes = R.drawable.triumphantlight
    ),
    SHINING_REVELRY(
        expansionId = "A2b",
        expansionName = R.string.expansion_A2b,
        imgRes = R.drawable.shiningrevelry
    ),
    CELESTIAL_GUARDIANS(
        expansionId = "A3",
        expansionName = R.string.expansion_A3,
        imgRes = R.drawable.celestialguardians
    ),
    EXTRADIMENSIONAL_CRISIS(
        expansionId = "A3a",
        expansionName = R.string.expansion_A3a, // TODO: add these expansion icons
    ),
    EEVEE_FOREST(
        expansionId = "A3b",
        expansionName = R.string.expansion_A3b,
    )
}

enum class Pack(
    val packId: String,
    @StringRes val packName: Int,
    @DrawableRes val imgRes: Int = R.drawable.baseline_person_24
) {
    PIKACHU_PACK(
        packId = "A1-Pikachu_Pack",
        packName = R.string.pack_A1_Pikachu
    ),
    CHARIZARD_PACK(
        packId = "A1-Charizard_Pack",
        packName = R.string.pack_A1_Charizard
    ),
    MEWTWO_PACK(
        packId = "A1-Mewtwo_Pack",
        packName = R.string.pack_A1_Mewtwo
    ),
    MYTHICAL_ISLAND_PACK(
        packId = "A1a-Mythical_Island_Pack",
        packName = R.string.pack_A1a
    ),
    DIALGA_PACK(
        packId = "A2-Dialga_Pack",
        packName = R.string.pack_A2_Dialga
    ),
    PALKIA_PACK(
        packId = "A2-Palkia_Pack",
        packName = R.string.pack_A2_Palkia
    ),
    TRIUMPHANT_LIGHT_PACK(
        packId = "A2a-Triumphant_Light_Pack",
        packName = R.string.pack_A2a
    ),
    SHINING_REVELRY_PACK(
        packId = "A2b-Shining_Revelry_Pack",
        packName = R.string.pack_A2b
    ),
    SOLGALEO_PACK(
        packId = "A3-Solgaleo_Pack",
        packName = R.string.pack_A3_Solgaleo
    ),
    LUNALA_PACK(
        packId = "A3-Lunala-Pack",
        packName = R.string.pack_A3_Lunala
    ),
    EXTRADIMENSIONAL_CRISIS_PACK(
        packId = "A3a-Extradimensiona_Crisis_Pack",
        packName = R.string.pack_A3a
    ),
    EEVEE_FOREST_PACK(
        packId = "A3b-Eevee_Forest_Pack",
        packName = R.string.pack_A3b
    )
}

