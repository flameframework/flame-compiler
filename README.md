flame-compiler
==============

## Cross platform app modelling

Please note that the current version of flame is still a prototype that only supports some of the features mentioned below.

Flame will be a framework for cross platform domain and interaction modeling. With flame you will create an app in three steps:

1. Model your domain and interactions (platform independent)
2. Automatically generate the body of your app for a supported platform (iOS, Android, Windows Mobile or web)
3. Implement the details specific to your app natively

Domain classes, data storage, simple views and flow through the application will be generated for you, so you will only have to deal with the details that are specific to your app. However, if you need more control, you can supply your own views or data layer. You can even extend flame to support any other platform. Because your eventual implementation of the app is native, you can make any app you want, with the full power of the underlying platform.

## Why Flame?
Native app development for multiple platforms is expensive. Existing cross platform solutions don't give you full control, often leading to bad performance or a poor user experience. Flame tries to bridge the gap between the two. Platform independent parts of the app only have to be defined once. But the actual implementation of the app can be done natively, giving you the full power of the underlying platform.
