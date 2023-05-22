package com.alekhin.fovmeapex.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.alekhin.fovmeapex.R

data class OnBoardingData(@DrawableRes val id: Int, @StringRes val titleText: Int, @StringRes val descriptionText: Int) {
    companion object {
        fun getOnBoardingDataList(): List<OnBoardingData> {
            return listOf(
            OnBoardingData(id = R.drawable.logo, titleText = R.string.title_one, descriptionText = R.string.description_one),
            OnBoardingData(id = R.drawable.logo, titleText = R.string.title_two, descriptionText = R.string.description_two),
            OnBoardingData(id = R.drawable.logo, titleText = R.string.title_three, descriptionText = R.string.description_three))
        }
    }
}