package cn.devmeteor.wechatseaching

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.devmeteor.wechatseaching.ui.theme.WeChatSeachingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DefaultPreview()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    var inputEmpty by remember { mutableStateOf(true) }
    WeChatSeachingTheme {
        Scaffold {
            Column {
                SearchArea { inputEmpty = it }
                Divider()
                if (inputEmpty) {
                    LazyColumn(content = {
                        item {
                            Column {
                                Container("常用小程序") {
                                    Row {
                                        ProgramInfo(Modifier.weight(1f))
                                        ProgramInfo(Modifier.weight(1f))
                                        ProgramInfo(Modifier.weight(1f))
                                        ProgramInfo(Modifier.weight(1f))
                                    }
                                }
                                Gap()
                                Container("推荐小程序") {
                                    ProgramItem()
                                }
                                Gap()
                                Container("发现更多") {
                                    Row {
                                        Keyword(word = "年轻人常用小程序", modifier = Modifier.weight(1f))
                                        Keyword(word = "热门小程序", modifier = Modifier.weight(1f))
                                    }
                                    Row {
                                        Keyword(word = "年轻人常用小程序", modifier = Modifier.weight(1f))
                                        Keyword(word = "热门小程序", modifier = Modifier.weight(1f))
                                    }
                                    Row {
                                        Keyword(word = "年轻人常用小程序", modifier = Modifier.weight(1f))
                                        Keyword(word = "热门小程序", modifier = Modifier.weight(1f))
                                    }
                                }
                                Gap()
                                Container("搜索历史") {
                                    HistoryItem(true)
                                    HistoryItem()
                                    HistoryItem()
                                    HistoryItem()
                                    HistoryItem()
                                    HistoryItem(isLast = true)
                                }
                            }
                        }
                    })
                } else {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .background(Color(0xffeeeeee))
                    ) {
                        SearchResult()
                        SearchResult()
                        SearchResult()
                        SearchResult(true)
                    }
                }
            }
        }
    }
}

@Composable
fun SearchArea(textChange: (empty: Boolean) -> Unit) {

    var textState by remember { mutableStateOf(TextFieldValue()) }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(15.dp, 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
            contentDescription = null
        )
        TextField(
            textState,
            {
                textChange(it.text.isBlank())
                textState = it
            },
            Modifier
                .weight(1f)
                .background(Color.Transparent)
                .padding(0.dp),
            leadingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_search_24),
                    contentDescription = null
                )
            },
            placeholder = {
                Text(text = "搜索小程序")
            },
            shape = MaterialTheme.shapes.small.copy(CornerSize(0.dp)),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )
        Text(text = "取消", Modifier.padding(10.dp), style = TextStyle(Color(69, 114, 145),fontSize = 18.sp))
    }
}

@Composable
fun Container(title: String, content: @Composable (ColumnScope.() -> Unit)? = null) {
    Column(
        Modifier
            .padding(15.dp, 15.dp, 0.dp, 15.dp)
            .fillMaxWidth()
    ) {
        Text(text = title, style = TextStyle(Color(0xff808080), 15.sp))
        Divider(Modifier.padding(0.dp, 10.dp))
        content?.invoke(this)
    }
}

@Composable
fun ProgramInfo(modifier: Modifier) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            bitmap = ImageBitmap.imageResource(id = R.drawable.ic_launcher),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .clip(CircleShape)
                .size(50.dp)
        )
        Text(
            text = "微信小程序",
            style = TextStyle(Color(0xff888888), 12.sp),
            modifier = Modifier.padding(0.dp, 5.dp)
        )
    }
}

@Composable
fun Gap() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xffeeeeee))
            .height(8.dp)
    )
}

@Composable
fun ProgramItem() {
    Row {
        Image(
            bitmap = ImageBitmap.imageResource(id = R.drawable.ic_launcher),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .clip(CircleShape)
                .size(50.dp)
        )
        Spacer(modifier = Modifier.width(15.dp))
        Column {
            Text(text = "微信小程序", style = TextStyle(fontSize = 18.sp))
            Text(
                text = "微信小程序简介……",
                style = TextStyle(Color(0xff888888), 15.sp),
                modifier = Modifier.padding(0.dp, 5.dp)
            )
            Text(
                text = "微信小程序类目",
                style = TextStyle(Color(0xff888888), 10.sp),
                modifier = Modifier
                    .background(Color(0xffeeeeee), RoundedCornerShape(3.dp))
                    .padding(3.dp)
            )
        }
    }
}

@Composable
fun Keyword(word: String, modifier: Modifier) {
    Text(
        word,
        modifier.padding(0.dp, 10.dp),
        style = TextStyle(Color(69, 114, 145), fontSize = 18.sp)
    )
}

@Composable
fun HistoryItem(isFirst: Boolean = false, isLast: Boolean = false) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = if (isFirst)
            Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp)
        else
            Modifier.padding(0.dp, 10.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_access_time_24),
            contentDescription = null,
            tint = Color(0xffcccccc)
        )
        Spacer(modifier = Modifier.width(15.dp))
        Text(text = "微信小程序", Modifier.weight(1f), style = TextStyle(fontSize = 18.sp))
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_close_24),
            contentDescription = null,
            tint = Color(0xffcccccc)
        )
        Spacer(modifier = Modifier.width(15.dp))
    }
    if (!isLast) {
        Divider()
    }
}

@Composable
fun SearchResult(isLast: Boolean = false) {
    Column(
        Modifier
            .background(Color.White)
            .padding(15.dp, 0.dp, 0.dp, 0.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(0.dp, 10.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_search_24),
                contentDescription = null,
                tint = Color(0xffcccccc)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = "微信小程序", Modifier.weight(1f), style = TextStyle(fontSize = 18.sp))
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_north_west_24),
                contentDescription = null,
                tint = Color(0xffcccccc)
            )
            Spacer(modifier = Modifier.width(15.dp))
        }
        if (!isLast) {
            Divider()
        }
    }
}