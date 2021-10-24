(ns simple-clojure.core
  (:require [ring.adapter.jetty :as ring-jetty]
            [clojure.tools.namespace.repl :refer [refresh]]
            [reitit.ring :as ring]
            [muuntaja.core :as m]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [ring.middleware.reload :refer [wrap-reload]])
  (:gen-class)
  (:import (java.util UUID)))

(def users (atom {}))

(defn string-handler [_]
  {:status 200
   :body "clojure for fun"})


(defn create-user [{user :body-params}]
  (let [id (str (UUID/randomUUID))
        users (->> (assoc user :id id)
                   (swap! users assoc id))]
    {:status 201
     :body users}))


(defn get-users [_] 
  {:status 200
   :body @users})


(defn get-user-by-id [{{:keys [id]} :path-params}]
  {:status 200
   :body (get @users id)})

(def app
  (ring/ring-handler
   (ring/router
    ["/"
     ["users/:id" get-user-by-id]
     ["users" {:get get-users
               :post create-user}]
     ["" string-handler]]
    {:data {:muuntaja m/instance 
            :middleware [muuntaja/format-middleware]}})))


(defn start [] (ring-jetty/run-jetty app {:port 4000 :join? false}))


(defn reset []
  (refresh))

(defn -main
  [& args] (start))
