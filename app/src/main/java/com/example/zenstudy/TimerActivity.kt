package com.example.zenstudy

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Colors
val SageGreen = Color(0xFF94FBAB)
val NeoWhite = Color(0xFFFFFFFF)
val NeoBlack = Color(0xFF000000)

class TimerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimerScreen()
        }
    }
}

enum class TimerTab(val label: String, val durationMinutes: Int) {
    FOKUS("Fokus", 25),
    PENDEK("Istirahat Pendek", 5),
    PANJANG("Istirahat Panjang", 15)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimerScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    var selectedTab by remember { mutableStateOf(TimerTab.FOKUS) }
    var timeLeft by remember { mutableStateOf(selectedTab.durationMinutes * 60) }
    var isTimerRunning by remember { mutableStateOf(false) }
    var currentSession by remember { mutableStateOf(1) }

    // Timer Logic
    LaunchedEffect(isTimerRunning, timeLeft) {
        if (isTimerRunning && timeLeft > 0) {
            delay(1000L)
            timeLeft--
        } else if (timeLeft == 0) {
            isTimerRunning = false
        }
    }

    // Update timer when tab changes (if not running)
    LaunchedEffect(selectedTab) {
        if (!isTimerRunning) {
            timeLeft = selectedTab.durationMinutes * 60
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(300.dp),
                drawerContainerColor = NeoWhite
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "ZENSTUDY",
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.Black,
                    fontSize = 20.sp
                )
                Divider(color = NeoBlack, thickness = 1.dp)
                NavigationDrawerItem(
                    label = { Text("Home") },
                    selected = false,
                    onClick = { context.startActivity(Intent(context, MainActivity::class.java)) }
                )
                NavigationDrawerItem(
                    label = { Text("Belajar") },
                    selected = false,
                    onClick = { context.startActivity(Intent(context, KatalogBelajarActivity::class.java)) }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "ZENSTUDY",
                            fontWeight = FontWeight.Black,
                            letterSpacing = 2.sp,
                            fontSize = 20.sp
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu", tint = NeoBlack)
                        }
                    },
                    actions = {
                        Box(
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .size(36.dp)
                                .neoBorder(shape = CircleShape)
                                .background(SageGreen, CircleShape)
                                .clickable {
                                    context.startActivity(Intent(context, ProfilActivity::class.java))
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text("K", fontWeight = FontWeight.Black, fontSize = 14.sp)
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = NeoWhite)
                )
            },
            bottomBar = {
                NeoBottomNavigation()
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                // Header
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Sesi Fokus",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = NeoBlack
                    )
                    Text(
                        text = "Atur ritme belajarmu hari ini",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Tab Control State
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .neoBorder(shape = RoundedCornerShape(12.dp))
                        .background(NeoWhite, RoundedCornerShape(12.dp))
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TimerTab.values().forEach { tab ->
                        val active = selectedTab == tab
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                                .padding(2.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (active) SageGreen else Color.Transparent)
                                .then(if (active) Modifier.neoBorder(shape = RoundedCornerShape(8.dp)) else Modifier)
                                .clickable { 
                                    selectedTab = tab
                                    isTimerRunning = false
                                    timeLeft = tab.durationMinutes * 60
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = tab.label.replace(" ", "\n"),
                                fontSize = 11.sp,
                                fontWeight = if (active) FontWeight.Black else FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                lineHeight = 13.sp,
                                color = NeoBlack
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Main Timer Card
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .neoShadow(shape = RoundedCornerShape(24.dp))
                        .neoBorder(shape = RoundedCornerShape(24.dp))
                        .background(NeoWhite, RoundedCornerShape(24.dp))
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "SESI $currentSession DARI 4",
                            fontWeight = FontWeight.Black,
                            fontSize = 14.sp,
                            color = NeoBlack
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            for (i in 1..4) {
                                Box(
                                    modifier = Modifier
                                        .size(12.dp)
                                        .neoBorder(shape = CircleShape, width = 1.dp)
                                        .background(if (i == currentSession) SageGreen else Color.Transparent, CircleShape)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(32.dp))
                        
                        // Circle Timer
                        Box(
                            modifier = Modifier
                                .size(220.dp)
                                .neoBorder(shape = CircleShape, width = 2.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                val minutes = timeLeft / 60
                                val seconds = timeLeft % 60
                                Text(
                                    text = String.format("%02d:%02d", minutes, seconds),
                                    fontSize = 60.sp,
                                    fontWeight = FontWeight.Black,
                                    color = NeoBlack
                                )
                                Text(
                                    "menit tersisa",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Main Action Button (MULAI FOKUS / JEDA)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .neoShadow(shape = RoundedCornerShape(12.dp))
                        .neoBorder(shape = RoundedCornerShape(12.dp))
                        .background(if (isTimerRunning) NeoWhite else SageGreen, RoundedCornerShape(12.dp))
                        .clickable { isTimerRunning = !isTimerRunning },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (isTimerRunning) "JEDA" else "MULAI FOKUS",
                        fontWeight = FontWeight.Black,
                        fontSize = 18.sp,
                        letterSpacing = 1.sp,
                        color = NeoBlack
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Secondary Buttons (Reset & Lewati Sesi)
                Row(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                            .neoShadow(shape = RoundedCornerShape(12.dp))
                            .neoBorder(shape = RoundedCornerShape(12.dp))
                            .background(NeoWhite, RoundedCornerShape(12.dp))
                            .clickable {
                                isTimerRunning = false
                                timeLeft = selectedTab.durationMinutes * 60
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Reset", fontWeight = FontWeight.Black, color = NeoBlack)
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp)
                            .neoShadow(shape = RoundedCornerShape(12.dp))
                            .neoBorder(shape = RoundedCornerShape(12.dp))
                            .background(NeoWhite, RoundedCornerShape(12.dp))
                            .clickable {
                                isTimerRunning = false
                                if (currentSession < 4) currentSession++ else currentSession = 1
                                selectedTab = TimerTab.PENDEK
                                timeLeft = selectedTab.durationMinutes * 60
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Lewati Sesi", fontWeight = FontWeight.Black, color = NeoBlack)
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Tips Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .neoBorder(shape = RoundedCornerShape(12.dp))
                        .background(Color(0xFFF1FFF4), RoundedCornerShape(12.dp))
                        .padding(16.dp)
                ) {
                    Row(verticalAlignment = Alignment.Top) {
                        Box(
                            modifier = Modifier
                                .width(4.dp)
                                .height(40.dp)
                                .background(SageGreen)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("Tips Zen", fontSize = 12.sp, fontWeight = FontWeight.Black, color = Color(0xFF4CAF50))
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                "Jauhkan HP-mu. Fokus 25 menit, lalu istirahat sejenak.",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Gray
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
fun NeoBottomNavigation() {
    val context = LocalContext.current
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        color = NeoWhite,
        border = BorderStroke(1.dp, Color(0xFFEEEEEE))
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem("Home", R.drawable.ic_nav_home) {
                context.startActivity(Intent(context, MainActivity::class.java))
            }
            BottomNavItem("Belajar", R.drawable.ic_nav_belajar) {
                context.startActivity(Intent(context, KatalogBelajarActivity::class.java))
            }
            BottomNavItem("Timer", R.drawable.ic_nav_timer, active = true) {
                // Already here
            }
            BottomNavItem("Profil", R.drawable.ic_nav_profil) {
                context.startActivity(Intent(context, ProfilActivity::class.java))
            }
        }
    }
}

@Composable
fun BottomNavItem(label: String, iconRes: Int, active: Boolean = false, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            tint = if (active) SageGreen else Color.Gray,
            modifier = Modifier.size(26.dp)
        )
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = if (active) FontWeight.Black else FontWeight.Bold,
            color = if (active) NeoBlack else Color.Gray
        )
    }
}

// Neobrutalism Extensions
fun Modifier.neoBorder(shape: androidx.compose.ui.graphics.Shape = RoundedCornerShape(0.dp), width: androidx.compose.ui.unit.Dp = 2.dp) = this.then(
    Modifier.border(width, NeoBlack, shape)
)

fun Modifier.neoShadow(
    shape: androidx.compose.ui.graphics.Shape = RoundedCornerShape(0.dp),
    offset: androidx.compose.ui.unit.Dp = 4.dp
): Modifier = this.drawBehind {
    val outline = shape.createOutline(size, layoutDirection, this)
    val shadowOffset = offset.toPx()
    
    // Draw the black shadow block
    drawContext.canvas.save()
    drawContext.canvas.translate(shadowOffset, shadowOffset)
    drawOutline(outline, color = NeoBlack)
    drawContext.canvas.restore()
}
