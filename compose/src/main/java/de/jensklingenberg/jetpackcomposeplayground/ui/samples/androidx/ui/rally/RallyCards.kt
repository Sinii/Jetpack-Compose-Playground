/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.jensklingenberg.jetpackcomposeplayground.ui.samples.androidx.ui.rally

import androidx.compose.Composable
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.*
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.ColoredRect
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.layout.Size
import androidx.ui.material.*
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.studies.rally.DrawAnimatedCircle
import androidx.ui.material.surface.Card
import java.util.Locale

/**
 * The Alerts card within the Rally Overview screen.
 */
@Composable
fun RallyAlertCard() {
    val openDialog = +state { false }
    val alertMessage = "Heads up, you've used up 90% of your Shopping budget for this month."

    if (openDialog.value) {
        RallyAlertDialog(
            onDismiss = {
                openDialog.value = false
            },
            bodyText = alertMessage,
            buttonText = "Dismiss".toUpperCase(Locale.getDefault())
        )
    }
    Card {
        Column {
            Ripple(bounded = true) {
                Clickable(onClick = { openDialog.value = true }) {
                    Container {
                        Row(
                            modifier = Spacing(12.dp) wraps ExpandedWidth,
                            arrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Alerts", style = (+MaterialTheme.typography()).subtitle2)
                            Button(text = "See All", onClick = { }, style = TextButtonStyle())
                        }
                    }
                }
            }
            Divider(
                Spacing(left = 12.dp, right = 12.dp),
                color = (+MaterialTheme.colors()).background,
                height = 2.dp
            )
            Ripple(bounded = true) {
                Clickable(onClick = { openDialog.value = true }) {
                    Container {
                        Row(Spacing(12.dp)) {
                            Text(
                                style = (+MaterialTheme.typography()).body1,
                                modifier = Flexible(1f),
                                text = alertMessage
                            )
                            // TODO: icons still don't work
//                            <vectorResource res=context.resources
//                                resId=androidx.ui.material.studies.R.drawable.sort_icon/>
                            Button(text = "Sort", onClick = { }, style = TextButtonStyle())
                        }
                    }
                }
            }
        }
    }
}

/**
 * The Accounts card within the Rally Overview screen.
 */
@Composable
fun RallyAccountsOverviewCard() {
    Card {
        Column {
            Column(modifier = Spacing(12.dp)) {
                Text(text = "Accounts", style = (+MaterialTheme.typography()).body1)
                Text(text = "$12,132.49", style = (+MaterialTheme.typography()).h3)
            }
            Divider(color = rallyGreen, height = 1.dp)
            Column(modifier = Spacing(12.dp)) {
                RallyAccountRow(
                    name = "Checking",
                    number = "1234",
                    amount = "2,215.13",
                    color = Color(0xFF005D57)
                )
                RallyDivider()
                RallyAccountRow(
                    name = "Home Savings",
                    number = "5678",
                    amount = "8,676.88",
                    color = Color(0xFF04B97F)
                )
                RallyDivider()
                RallyAccountRow(
                    name = "Car Savings",
                    number = "9012",
                    amount = "987.48",
                    color = Color(0xFF37EFBA)
                )
                RallyDivider()
                Button(text = "See All", style = TextButtonStyle())
            }
        }
    }
}

/**
 * The Accounts composable used in a separate tab.
 */
@Composable
fun RallyAccountsCard() {
    VerticalScroller {
        Column {
            Stack(Spacing(16.dp)) {
                val accountsProportion = listOf(0.595f, 0.045f, 0.095f, 0.195f, 0.045f)
                val colors = listOf(0xFF1EB980, 0xFF005D57, 0xFF04B97F, 0xFF37EFBA,
                    0xFFFAFFBF).map { Color(it) }
                Container( height = 300.dp, expanded = true) {
                    DrawAnimatedCircle(accountsProportion, colors)
                }
                Column() {
                    Text(
                        text = "Total",
                        style = (+MaterialTheme.typography()).body1,
                        modifier = Gravity.Center
                    )
                    Text(
                        text = "$12,132.49",
                        style = (+MaterialTheme.typography()).h3,
                        modifier = Gravity.Center
                    )
                }
            }
            Spacer(Height(10.dp))
            Card {
                Column(modifier = Spacing(12.dp)) {
                    RallyAccountRow(
                        name = "Checking",
                        number = "1234",
                        amount = "2,215.13",
                        color = Color(0xFF005D57)
                    )
                    RallyDivider()
                    RallyAccountRow(
                        name = "Home Savings",
                        number = "5678",
                        amount = "8,676.88",
                        color = Color(0xFF04B97F)
                    )
                    RallyDivider()
                    RallyAccountRow(
                        name = "Car Savings",
                        number = "9012",
                        amount = "987.48",
                        color = Color(0xFF37EFBA)
                    )
                    RallyDivider()
                    RallyAccountRow(
                        name = "Vacation",
                        number = "3456",
                        amount = "253",
                        color = Color(0xFF005D57)
                    )
                }
            }
        }
    }
}

fun Spacer(height: LayoutModifier) {

}

/**
 * A row within the Accounts card in the Rally Overview screen.
 */
@Composable
fun RallyAccountRow(name: String, number: String, amount: String, color: Color) {
    FlexRow(Spacing(top = 12.dp, bottom = 12.dp)) {
        inflexible {
            AccountIndicator(color = color)
            Spacer(Width(8.dp))
            Column {
                Text(text = name, style = (+MaterialTheme.typography()).body1)
                Text(text = "•••••$number", style = (+MaterialTheme.typography()).subtitle1)
            }
        }
        expanded(flex = 1.0f) {
            Spacer(Size(width = 0.dp, height = 0.dp))
        }
        inflexible {
            Text(text = "$ $amount", style = (+MaterialTheme.typography()).h6)
        }
    }
}

/**
 * A vertical colored line that is used in a [RallyAccountRow] to differentiate accounts.
 */
@Composable
fun AccountIndicator(color: Color) {
    ColoredRect(color = color, width = 4.dp, height = 36.dp)
}

/**
 * The Bills card within the Rally Overview screen.
 */
@Composable
fun RallyBillsOverviewCard() {
    Card {
        Column {
            Column(modifier = Spacing(12.dp)) {
                Text(text = "Bills", style = (+MaterialTheme.typography()).subtitle2)
                Text(text = "$1,810.00", style = (+MaterialTheme.typography()).h3)
            }
            Divider(color = rallyGreen, height = 1.dp)
            // TODO: change to proper bill items
            Column(modifier = Spacing(12.dp)) {
                RallyAccountRow(
                    name = "RedPay Credit",
                    number = "Jan 29",
                    amount = "-45.36",
                    color = Color(0xFF005D57)
                )
                RallyDivider()
                RallyAccountRow(
                    name = "Rent",
                    number = "Feb 9",
                    amount = "-1,200.00",
                    color = Color(0xFF04B97F)
                )
                RallyDivider()
                RallyAccountRow(
                    name = "TabFine Credit",
                    number = "Feb 22",
                    amount = "-87.33",
                    color = Color(0xFF37EFBA)
                )
                RallyDivider()
                Button(text = "See All", style = TextButtonStyle())
            }
        }
    }
}

/**
 * The Accounts composable used in a separate tab.
 */
@Composable
fun RallyBillsCard() {
    VerticalScroller {
        Column {
            Stack(Spacing(16.dp)) {
                val accountsProportion = listOf(0.65f, 0.25f, 0.03f, 0.05f)
                val colors = listOf(0xFF1EB980, 0xFF005D57, 0xFF04B97F, 0xFF37EFBA).map {
                    Color(it)
                }
                Container( height = 300.dp, expanded = true) {
                    DrawAnimatedCircle(accountsProportion, colors)
                }
                Column() {
                    Text(
                        text = "Due",
                        style = (+MaterialTheme.typography()).body1,
                        modifier = Gravity.Center
                    )
                    Text(
                        text = "$1,810.00",
                        style = (+MaterialTheme.typography()).h3,
                        modifier = Gravity.Center
                    )
                }
            }
            Spacer(Height(10.dp))
            Card {
                // TODO: change to proper bill items
                Column(modifier = Spacing(12.dp)) {
                    RallyAccountRow(
                        name = "RedPay Credit",
                        number = "Jan 29",
                        amount = "-45.36",
                        color = Color(0xFF005D57)
                    )
                    RallyDivider()
                    RallyAccountRow(
                        name = "Rent",
                        number = "Feb 9",
                        amount = "-1,200.00",
                        color = Color(0xFF04B97F)
                    )
                    RallyDivider()
                    RallyAccountRow(
                        name = "TabFine Credit",
                        number = "Feb 22",
                        amount = "-87.33",
                        color = Color(0xFF37EFBA)
                    )
                    RallyDivider()
                    RallyAccountRow(
                        name = "ABC Loans",
                        number = "Feb 29",
                        amount = "-400.00",
                        color = Color(0xFF005D57)
                    )
                }
            }
        }
    }
}

@Composable
fun RallyDivider() = Divider(color = (+MaterialTheme.colors()).background, height = 2.dp)
