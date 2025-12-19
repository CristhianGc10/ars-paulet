package com.ars.paulet.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ars.paulet.presentation.screens.home.HomeScreen
import com.ars.paulet.presentation.screens.course.CourseScreen
import com.ars.paulet.presentation.screens.lesson.LessonScreen
import com.ars.paulet.presentation.screens.settings.SettingsScreen
import com.ars.paulet.presentation.screens.profile.ProfileScreen

/**
 * Rutas de navegación de la aplicación
 */
sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Course : Screen("course/{courseId}") {
        fun createRoute(courseId: String) = "course/$courseId"
    }
    data object Lesson : Screen("lesson/{lessonId}") {
        fun createRoute(lessonId: String) = "lesson/$lessonId"
    }
    data object Activity : Screen("activity/{activityId}") {
        fun createRoute(activityId: String) = "activity/$activityId"
    }
    data object Settings : Screen("settings")
    data object Profile : Screen("profile")
}

/**
 * Navegación principal de la aplicación
 */
@Composable
fun ArsPauletNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        // Pantalla principal
        composable(Screen.Home.route) {
            HomeScreen(
                onCourseClick = { courseId ->
                    navController.navigate(Screen.Course.createRoute(courseId))
                },
                onSettingsClick = {
                    navController.navigate(Screen.Settings.route)
                },
                onProfileClick = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }
        
        // Pantalla de curso
        composable(
            route = Screen.Course.route,
            arguments = listOf(
                navArgument("courseId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val courseId = backStackEntry.arguments?.getString("courseId") ?: ""
            CourseScreen(
                courseId = courseId,
                onLessonClick = { lessonId ->
                    navController.navigate(Screen.Lesson.createRoute(lessonId))
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        
        // Pantalla de lección
        composable(
            route = Screen.Lesson.route,
            arguments = listOf(
                navArgument("lessonId") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val lessonId = backStackEntry.arguments?.getString("lessonId") ?: ""
            LessonScreen(
                lessonId = lessonId,
                onActivityClick = { activityId ->
                    navController.navigate(Screen.Activity.createRoute(activityId))
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        
        // Pantalla de configuración
        composable(Screen.Settings.route) {
            SettingsScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        
        // Pantalla de perfil
        composable(Screen.Profile.route) {
            ProfileScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

/**
 * Extensiones para navegación
 */
fun NavHostController.navigateToHome() {
    navigate(Screen.Home.route) {
        popUpTo(Screen.Home.route) { inclusive = true }
    }
}

fun NavHostController.navigateToCourse(courseId: String) {
    navigate(Screen.Course.createRoute(courseId))
}

fun NavHostController.navigateToLesson(lessonId: String) {
    navigate(Screen.Lesson.createRoute(lessonId))
}
