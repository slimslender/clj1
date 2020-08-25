 (defproject clj1 "0.1.1-SNAPSHOT"
   :description "IXME: write description"
   :dependencies [[org.clojure/clojure "1.10.0"]
                  [metosin/compojure-api "1.1.13" :exclusions [commons-codec com.fasterxml.jackson.core/jackson-core]]
                  [cheshire "5.6.3"]
                  [clj-time "0.14.3"]
                  [ring/ring-core "1.6.2"]]
   :ring {:handler clj1.handler/app}
   :jar-name "server.jar"
   :target-path "target/%s/"
   :repositories [["releases" {:url      "https://sforzando.artifactoryonline.com/sforzando/libs-release-local"
                               :username [:gpg :env/artifactory_user]
                               :password [:gpg :env/artifactory_pwd]}]
                  ["plugins" {:url      "https://sforzando.artifactoryonline.com/sforzando/plugins-release"
                              :username [:gpg :env/artifactory_user]
                              :password [:gpg :env/artifactory_pwd]}]]
   :container {:name       "pochta"
               :dockerfile "/docker"
               :hub        "sforzando-dockerv2-local.jfrog.io"}
   :dynamodb-local {:port 6798
                    :in-memory? true
                    :shared-db? true}
   :plugins [[com.livingsocial/lein-dependency-check "1.1.2"]]

   :dependency-check {:properties-file "dependency-check.properties"
                      :suppression-file "suppressions.xml"}

   :profiles {:dev {:dependencies [[javax.servlet/javax.servlet-api "3.1.0"]]
                    :plugins [[lein-metajar "0.1.1"]
                              [lein-set-version "0.4.1"]
                              [lein-dynamodb-local "0.2.10"]]}
              :metajar {:direct-linking true
                        :aot :all
                        :source-paths   ["env/prod/clj"]
                        :resource-paths ["env/prod/resources"]}})
