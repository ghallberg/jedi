(ns jedi.handlers
    (:require [re-frame.core :as re-frame]
              [jedi.db :as db]))

(def fetch-jedi)

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/register-handler
  :up-button-clicked)

(re-frame/register-handler
  :down-button-clicked)
