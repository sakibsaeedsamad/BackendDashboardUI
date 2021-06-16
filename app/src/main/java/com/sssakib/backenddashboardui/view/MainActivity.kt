package com.sssakib.backenddashboardui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Scaffold
import androidx.ui.res.stringResource
import com.sssakib.backenddashboardui.R
import com.sssakib.backenddashboardui.components.TopAppBarMenu
import com.sssakib.backenddashboardui.components.AppTopBar
import com.sssakib.backenddashboardui.components.showError
import com.sssakib.backenddashboardui.components.showLoading
import com.sssakib.backenddashboardui.data.Result
import com.sssakib.backenddashboardui.data.observe
import com.sssakib.backenddashboardui.view.lightThemeColors
import com.sssakib.backenddashboardui.view.showDashboard
import com.sssakib.backenddashboardui.view.themeTypography

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val topBarMenu = TopAppBarMenu()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setContent {
            myFun(viewModel, topAppBarMenu = topBarMenu)
            viewModel.loadData(topBarMenu.showRandomDashboard)
        }
    }
}

@Composable
fun myFun(viewModel: MainViewModel, topAppBarMenu: TopAppBarMenu) {
    MaterialTheme(colors = lightThemeColors, typography = themeTypography) {
        Scaffold(topAppBar = {
            AppTopBar(name = stringResource(id = R.string.app_name), menu = topAppBarMenu)
        }) {
            when (val data = observe(data = viewModel.dashboardItems)) {
                is Result.Loading -> {
                    showLoading()
                }
                is Result.Success -> {
                    showDashboard(
                        data = data.data ?: emptyList()
                    )
                }
                is Result.Failure -> {
                    showError(
                        message = data.error.message ?: "",
                        onRetry = { viewModel.loadData(topAppBarMenu.showRandomDashboard) })
                }
            }
        }
    }
}
