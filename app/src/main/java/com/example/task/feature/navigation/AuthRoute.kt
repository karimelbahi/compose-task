package com.example.task.feature.navigation

/*

import java.net.URLDecoder

fun authRoute(
    navBuilder: NavGraphBuilder,
    navController: NavHostController,
    datastore: DataStoreUserPreferences,
    mainActivity: MainActivity
) {
    with(navBuilder) {
        composable(route = AuthScreens.OnBoardScreen.route) {
            OnBoardMain(navController)
        }
        composable(route = AuthScreens.RequestOtpScreen.route) {
            val viewModel: PhoneViewModel = hiltViewModel()
            val state = viewModel.state
            RequestOtpScreen(
                navController = navController,
                stateParams = state,
                onEvent = viewModel::onEvent
            )
        }

        composable(route = AuthScreens.OtpScreen.route.plus("/{param}")) {
            val param = it.arguments?.getString("param")
            val viewModel: OTPViewModel = hiltViewModel()
            val state = viewModel.state
            OtpScreen(
                navController = navController,
                stateParams = state,
                onEvent = viewModel::onEvent,
                param
            )
        }

        composable(route = AuthScreens.RegisterParentScreen.route) {
            val viewModel: RegisterParentViewModel = hiltViewModel()
            val state = viewModel.state
            RegisterParentScreenScreen(
                navController = navController,
                stateParams = state,
                onEvent = viewModel::onEvent
            ) {
                viewModel.payByFawry(mainActivity)
            }
        }

        composable(route = AuthScreens.RegisterKidScreen.route) {
            val viewModel: RegisterKidsViewModel = hiltViewModel()
            val state = viewModel.state
            RegisterKidScreen(
                navController = navController,
                stateParams = state,
                onEvent = viewModel::onEvent
            ) {
                viewModel.payByFawry(mainActivity)
            }
        }

        composable(route = AuthScreens.WebView.route.plus("/{param}")) {
            val param = it.arguments?.getString("param")
            val url = URLDecoder.decode(param, "UTF-8")
            MyWebViewScreen(url.convertNullToEmpty(), navController = navController)
        }

        composable<AuthScreens.VerificationFlowScreen> { backstackEntry ->
            val parameters = backstackEntry.toRoute<AuthScreens.VerificationFlowScreen>()
            val toolbarTitle = parameters.toolbarTitle
            val title = parameters.title
            val subtitle = parameters.subtitle
            val viewModel: VerificationFlowViewModel = hiltViewModel()
            val state = viewModel.state
            VerificationFlowScreen(
                toolbarTitle = toolbarTitle,
                title = title,
                subtitle = subtitle,
                navController = navController,
                stateParams = state,
                onEvent = viewModel::onEvent
            )

        }
    }

}
*/
