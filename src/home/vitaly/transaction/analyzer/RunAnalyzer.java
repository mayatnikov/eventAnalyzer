/**************************************************************************************
 * Copyright (C) 2014 Vitaly&Pavel team. All rights reserved.                         *
 *                                                                                    *
 * ---------------------------------------------------------------------------------- *
 * The software in this package is published under the terms of the GPL license       *
 * a copy of which has been included with this distribution in the license.txt file.  *
 **************************************************************************************/
package home.vitaly.transaction.analyzer;

import org.apache.log4j.Logger;

public class RunAnalyzer {
    static Logger LOG = Logger.getLogger(RunAnalyzer.class.getName());

	public static void main(String... args) throws Exception {
        LOG.info("Start analyzer ...");
			org.apache.camel.spring.Main.main(args);
	}
}
