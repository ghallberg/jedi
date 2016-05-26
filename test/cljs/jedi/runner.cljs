(ns jedi.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [jedi.core-test]))

(doo-tests 'jedi.core-test)
