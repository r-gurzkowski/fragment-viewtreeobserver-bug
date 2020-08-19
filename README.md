The purpose of this repository is to demonstrate a problem with a `viewTreeObserver` obtained from
the fragments view during `onDestroyView` callback. It started happening after updating the
`androidx.fragment:fragment` artifact to version 1.2.0.

# Scenario
1. A `OnGlobalLayoutListener` is added during `onViewCreated` callback.
2. The listener is removed during `onDestroyView`
3. The listener is still being called after `onDestroyView` - unexpected

# Code
```kotlin
class MainFragment : Fragment() {
    private val onLayoutObserver = ViewTreeObserver.OnGlobalLayoutListener {
        if (view == null) {
            throw IllegalStateException()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.viewTreeObserver.addOnGlobalLayoutListener(onLayoutObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // this has no effect
        requireView().viewTreeObserver.removeOnGlobalLayoutListener(onLayoutObserver)
    }
}
```

# Fix
The scenario above can be fixed using `viewTreeObserver` obtained from the `decorView` instead
of the fragments view.