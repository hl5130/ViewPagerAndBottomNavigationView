# ViewPagerAndBottomNavigationView
### 这个项目使用的是BottomNavigationView 和 ViewPager+Fragment，组合成的UI视图。
### 注意事项：
1. 禁止ViewPager滑动（需要滑动注释掉就行了）
```
 mViewPagerContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
```

2. 点击Button时不需要ViewPager的滑动效果
```
mViewPagerContent.setCurrentItem(0,false);
```

3. 不需要BottomNavigationView的放大效果（BottomNavigationViewHelper是自定义的一个类，主要用于反射）
```
BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
```
4. 详细操作，根据项目中的TODO指示来执行
