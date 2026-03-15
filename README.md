# Android SwipeRefreshLayout 下拉刷新演示

## 简介

本 Demo 演示 SwipeRefreshLayout 的基本用法，展示如何实现下拉刷新功能。

## 基本原理

SwipeRefreshLayout 是 Android Support Library 提供的下拉刷新组件，包裹需要刷新功能的 View（通常是 RecyclerView 或 ListView）。用户下拉时会显示刷新指示器，触发刷新回调。

核心功能：
- 下拉显示刷新指示器
- 自动处理刷新状态
- 支持与 ListView/RecyclerView 配合

## 启动和使用

### 环境要求
- Android Studio
- JDK 17
- Gradle 8.x
- SwipeRefreshLayout 库

### 安装和运行

1. 用 Android Studio 打开项目
2. 连接 Android 设备或模拟器
3. 点击 Run 运行

### 使用方法
- 向下拉动屏幕，触发刷新
- 刷新完成后显示"数据已刷新"

## 教程

### 什么是 SwipeRefreshLayout？

SwipeRefreshLayout 是 Google 官方提供的下拉刷新组件，提供标准的 Material Design 下拉刷新体验。它包裹需要刷新功能的子 View，用户下拉时显示圆形刷新指示器。

### 基本用法

1. 在布局中使用 SwipeRefreshLayout：

```xml
<androidx.swiperefreshlayout.widget.SwipeRefreshLayout
    android:id="@+id/swipeRefresh"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <RecyclerView
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</androidx.swiperefreshlayout.widget.SwipeRefreshLayout>
```

2. 设置刷新监听器：

```kotlin
swipeRefresh.setOnRefreshListener {
    // 执行刷新操作
    // 刷新完成后设置 isRefreshing = false
    swipeRefresh.isRefreshing = false
}
```

### 自定义指示器颜色

```kotlin
swipeRefresh.setColorSchemeResources(
    android.R.color.holo_blue_bright,
    android.R.color.holo_green_light,
    android.R.color.holo_orange_light
)
```

### 注意事项

1. **包裹单个子 View**：SwipeRefreshLayout 只能包裹一个子 View
2. **刷新状态**：刷新完成后必须设置 isRefreshing = false
3. **禁用刷新**：可以使用 setEnabled(false) 禁用下拉刷新

## 关键代码详解

### MainActivity.kt

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. 获取 SwipeRefreshLayout 和 TextView 组件
        val swipeRefresh = findViewById<SwipeRefreshLayout>(R.id.swipeRefresh)
        val textView = findViewById<TextView>(R.id.textView)

        // 2. 设置下拉刷新监听器
        swipeRefresh.setOnRefreshListener {
            // 3. 使用 Handler 模拟异步刷新操作
            Handler().postDelayed({
                // 更新数据
                textView.text = "数据已刷新"
                // 4. 重要：刷新完成后必须设为 false
                // 这会隐藏刷新指示器
                swipeRefresh.isRefreshing = false
            }, 2000)  // 模拟 2 秒的刷新时间
        }
    }
}
```

### activity_main.xml

```xml
<!-- SwipeRefreshLayout 包裹内容 -->
<androidx.swiperefreshlayout.widget.SwipeRefreshLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/swipeRefresh"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!-- 内部布局：垂直线性布局 -->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        android:padding="16dp">

        <!-- 标题 -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="SwipeRefreshLayout 下拉刷新演示"
            android:textSize="20sp"
            android:textStyle="bold"
            android:gravity="center" />

        <!-- 显示刷新状态的 TextView -->
        <TextView
            android:id="@+id/textView"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="下拉刷新"
            android:paddingTop="24dp" />

    </LinearLayout>

</androidx.swiperefreshlayout.widget.SwipeRefreshLayout>
```

### 工作流程

1. 用户下拉屏幕
2. SwipeRefreshLayout 显示圆形指示器
3. 触发 onRefresh 回调
4. 执行异步刷新操作
5. 刷新完成后设置 isRefreshing = false
6. 指示器消失，数据已更新
