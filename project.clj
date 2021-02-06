(defproject ep2 "0.1.0-SNAPSHOT"
  :description "EP2 - logica de programacao"
  :url "https://github.com/MComp413/EP2-logica-de-programacao"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [ [org.clojure/clojure "1.10.1"]
                  [org.clojure/data.json "1.0.0"] ]
  :main ^:skip-aot ep2.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
