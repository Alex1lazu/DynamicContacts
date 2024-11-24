package com.example.rfbank.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rfbank.components.ContactsTopBar
import com.example.rfbank.components.EditButton
import com.example.rfbank.components.UserEntry
import com.example.rfbank.ui.theme.RFGray
import com.example.rfbank.viewmodel.ContactsViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@Composable
fun ContactsScreen(
    contactsViewModel: ContactsViewModel = hiltViewModel()
) {
    val state by contactsViewModel.state.collectAsState()
    val lazyListState = rememberLazyListState()

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.layoutInfo }
            .map { layoutInfo ->
                val visibleItems = layoutInfo.visibleItemsInfo
                if (visibleItems.isNotEmpty()) {
                    val lastVisibleIndex = visibleItems.last().index
                    lastVisibleIndex >= state.usersState.users.size - 3
                } else {
                    true
                }
            }
            .distinctUntilChanged()
            .collect { isEmptyOrReachedEnd ->
                if (isEmptyOrReachedEnd) {
                    contactsViewModel.fetchData()
                }
            }
    }

    Scaffold(topBar = {
        ContactsTopBar()
    }, containerColor = RFGray) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            EditButton(modifier = Modifier.align(Alignment.BottomEnd).zIndex(1f))
            Column(modifier = Modifier.padding(top = innerPadding.calculateTopPadding())) {
                LazyColumn(state = lazyListState) {
                    items(state.usersState.users) { user ->
                        UserEntry(user)
                    }
                    if (state.isLoadingUsers) {
                        item {
                            Box(modifier = Modifier.fillMaxWidth()) {
                                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center).padding(30.dp))
                            }
                        }
                    }
                }
            }
        }
    }

}

