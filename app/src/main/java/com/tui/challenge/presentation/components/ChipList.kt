package com.tui.challenge.presentation.components

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.tui.challenge.R

@Preview
@Composable
fun ChipListPreview() {
    ChipList(
        chips = listOf("Chip 1", "Chip 2", "Chip 3")
    )
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterialApi::class)
@Composable
fun ChipList(chips: List<String>) {
    FlowRow(
        modifier = Modifier
            .padding(vertical = dimensionResource(id = R.dimen.small_padding))
            .testTag(ChipListTestTag),
    ) {
        for (chip in chips) {
            if (chip.isEmpty()) continue

            Chip(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.small_padding)),
                onClick = {}
            ) {
                Text(text = chip)
            }
        }
    }
}

const val ChipListTestTag = "ChipListTestTag"