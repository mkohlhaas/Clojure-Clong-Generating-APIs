(defproject f "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "https://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.12.2"]
                 [com.phronemophobic/clong "1.4.3"]
                 [org.bytedeco/llvm-platform "16.0.4-1.5.9"]]
  :repl-options {:init-ns f.core}
  :jvm-opts ["--enable-native-access=ALL-UNNAMED"])
