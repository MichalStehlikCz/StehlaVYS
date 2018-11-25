/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provys.sqlbuilder.sqlbuilder;

import com.provys.sqlbuilder.sqlbuilder.SqlColumn;

/**
 * Represents pair of columns forming join condition for SqlFromElem.
 * 
 * @author stehlik
 */
class SqlColumnJoin {
        private final SqlColumn source;
        private final SqlColumn target;
        
        public SqlColumnJoin(SqlColumn source, SqlColumn target) {
            this.source = source;
            this.target = target;
        }

        /**
         * @return the source
         */
        public SqlColumn getSource() {
            return source;
        }

        /**
         * @return the toColumn
         */
        public SqlColumn getTarget() {
            return target;
        }

}
