# Genos

[![Download](https://api.bintray.com/packages/nyssance/maven/genos/images/download.svg)](https://bintray.com/nyssance/maven/genos/_latestVersion)

👊 Genos makes it very easy to build better mobile apps more quickly and with less code.
For more information please see [the website][genos].

[Genos for iOS](https://github.com/nyssance/genos)

- [Genos Samples](https://github.com/nyssance/genos-samples)

## Installation

### bulid.gradle

```groovy
implementation 'com.nyssance.genos:genos:1.2.1'
```

### bulid.gradle.kts

```kotlin
implementation("com.nyssance.genos:genos:1.2.1")
```

## Features

Genos integrate google architecture. just use. if your need learn more info about how genos work, and mvvm, repository , viewmodel etc., see [link](https://developer.android.com/topic/libraries/architecture)

1. Rules

- Activity just as an container, include app bar and drawer/bottom navigation, and one fragment or more.
- Fragment have two type: list and detail.
  - list for REST list api, like https://www.yourdomain.com/api/v1/users/, list include default `listView`, `adapter`
  - detail for REST detail api, like https://www.yourdomain,com/api/v1/users/{:user_id}/
  - call in fragment is a call of it, it's a [Retrofit][retrofit] call
- Repository is for load data.
- ViewModel is for bind data and view.

2. How to use

Create a list fragment, override three methods, 15 lines code, that's all you need to do.
```kotlin
...
import genos.ui.fragment.generic.List
import genos.ui.viewholder.DefaultHolder

class UserList : List<User, DefaultHolder>() {
    override fun onCreate() {
        call = API.userList(page)  // A retrofit call of this fragment.
    }

    override fun onDisplayItem(item: User, view: DefaultHolder, viewType: Int) {
        view.setImage(holder.icon, item.avatarUrl)
        view.title.text = item.username
    }

    override fun onOpenItem(item: User) {
        // StartActivity or do anything when click item.
    }
}
```

Create a bottom navigation with three buttons, 10 lines.
```kotlin
class MainActivity : TabBarActivity() { // If you need a drawer navigation, just use DrawerActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(fragments) {
            append(R.id.navigation_home, UserList())
            append(R.id.navigation_discover, PlaceholderFragment.instance("2"))
            append(R.id.navigation_me, PlaceholderFragment.instance("3"))
        }
    }
}
```

## Tutorial

[Develop an app in 10 minutes][genos].

## Architecture

```
genos
├── Global.kt                               Global config.
├── Helper.kt
├── Utils.kt
├── extension
│   ├── Fragment+Extension.kt
│   └── ImageView+Extension.kt
├── model
│   ├── BaseItem.kt
│   └── Item.kt
├── repository
│   ├── HttpRepository.kt                   Default http repository.
│   ├── HttpUtils.kt
│   ├── IRepository.kt
│   ├── NetworkState.kt
│   └── Status.kt
├── ui
│   ├── BaseAdapter.kt                      Default Adapter for list fragment.
│   ├── BaseViewModel.kt                    Default ViewModel for list and detail fragment.
│   ├── activity
│   │   ├── AppBarActivity.kt               Activity with an app bar.
│   │   ├── CollapsingActivity.kt           Activity with a collapsing app bar.
│   │   ├── DrawerActivity.kt               Activity with drawer.
│   │   ├── TabBarActivity.kt               Activity with bottom navigation.
│   │   ├── WebActivity.kt
│   │   ├── base                            Design your activity by extends activity in base.
│   │   │   ├── BaseActivity.kt
│   │   │   ├── NavigationActivity.kt
│   │   │   └── SingleActivity.kt
│   ├── fragment
│   │   ├── ActionSheet.kt
│   │   ├── Dialog.kt
│   │   ├── PlaceholderFragment.kt
│   │   ├── ViewPagerFragment.kt            Fragment with a view pager.
│   │   ├── base                            Design your fragment by extends fragment in base.
│   │   │   ├── BaseFragment.kt
│   │   │   ├── ListFragment.kt
│   │   │   ├── LoaderFragment.kt
│   │   │   ├── ObjectFragment.kt
│   │   │   └── RecyclerViewFragment.kt
│   │   └── generic
│   │       ├── Detail.kt                   Fragment for detail.
│   │       ├── List.kt                     Fragment with a linear layout, use for stand list, one item per line.
│   │       ├── GridViewList.kt             Fragment with a grid layout, user for grid list.
│   │       ├── StaggeredGridViewList.kt    Fragment with a staggered grid layout, use for waterfall list.
│   │       └── TableViewDetail.kt
│   └── viewholder
│       ├── BaseHolder.kt                   Base holder.
│       └── Holder.kt                       A holder with icon, title, subtitle, accessory.
├── vendor
│   └── MessageEvent.kt                     Utils for EventBus.
```

## Vendor

- Android
  - [Android Jetpack](https://developer.android.com/jetpack/)
- Others
  - [Retrofit][retrofit]
  - [Glide](https://github.com/bumptech/glide)
  - [EventBus](https://github.com/greenrobot/EventBus)
  - [Logger](https://github.com/orhanobut/logger)
  - [AgentWeb](https://github.com/Justson/AgentWeb)

Special thanks [bintray-release](https://github.com/novoda/bintray-release), who save my life.

## License

Genos is released under the Apache license. [See LICENSE](https://github.com/nyssance/genos/blob/master/LICENSE) for details.

[genos]: https://nyssance.github.io/genos
[retrofit]: https://square.github.io/retrofit/
[2]: https://search.maven.org/remote_content?g=com.nyssance.genos&a=genos&v=LATEST
[10]: https://developer.android.com/studio/projects/create-project
