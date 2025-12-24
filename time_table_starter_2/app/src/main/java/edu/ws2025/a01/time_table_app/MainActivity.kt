package edu.ws2025.a01.time_table_app

import android.app.Application
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import edu.ws2025.a01.time_table_app.ui.theme.Time_table_appTheme
import java.time.LocalDate

//Hilt依存関係注入用
@HiltAndroidApp
class MyApplication : Application()

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold { innerPadding ->
                App(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}


//アプリ画面全体用Composable関数
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun App(modifier: Modifier = Modifier) {
    //画面を切り替えるためのコントローラ
    val navController = rememberNavController()
    val insets = WindowInsets.safeDrawing.asPaddingValues()
    var targetDate = LocalDate.now()
    //全体の画面遷移用のNavHost
    NavHost(
        navController = navController, startDestination = "HomePage",
        modifier = Modifier.padding(
            top = insets.calculateTopPadding(),
            bottom = insets.calculateBottomPadding()
        )
    ) {
        //初期ページ
        composable("HomePage") {
         //TODO 1.画面の登録
            HomePage {
                targetDate = it
                navController.navigate("AddDataPage")
            }
        }
        //データ追加ページ
        composable("AddDataPage") {
            //TODO 1.画面の登録
            AddDataPage(targetDate = targetDate) {
                navController.navigate("HomePage")
            }
        }
    }
}

