import SwiftUI
import Shared

struct ContentView: View {
    @State private var showContent = false
    @State private var value: String = ""

    // Initialize Greeting instance
    let greeting = Greeting()

    var body: some View {
        VStack {
            Button("Click me!") {
                withAnimation {
                    showContent.toggle()
                }
            }

            if showContent {
                VStack(spacing: 16) {
                    
                    Text("SwiftUI: \(value)")
                }
                .transition(.move(edge: .top).combined(with: .opacity))
            }
        }
        .onAppear {
            // Call greetWithPosts to fetch data
            greeting.greetWithPosts { result in
                // Update the value state when data is fetched
                self.value = result
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
