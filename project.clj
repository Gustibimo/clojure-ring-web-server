(defproject simple-clojure "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [javax.servlet/servlet-api "2.5"]
                 [ring "1.9.0"]
                 [metosin/reitit "0.5.12"]
                 [metosin/muuntaja "0.6.8"]
                 [org.clojure/tools.namespace  "1.1.0"]]
                
  :main ^:skip-aot simple-clojure.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :ring {:handler simple-clojure.core/handler}
  :plugins [[lein-ring "0.12.5"]])

  

