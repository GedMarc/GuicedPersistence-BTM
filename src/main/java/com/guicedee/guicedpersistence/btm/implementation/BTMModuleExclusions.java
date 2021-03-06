package com.guicedee.guicedpersistence.btm.implementation;

import com.guicedee.guicedinjection.interfaces.IGuiceScanModuleExclusions;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

public class BTMModuleExclusions
		implements IGuiceScanModuleExclusions<BTMModuleExclusions>
{
	@Override
	public @NotNull Set<String> excludeModules()
	{
		Set<String> strings = new HashSet<>();
		strings.add("com.guicedee.guicedpersistence.btm");

		strings.add("btm");
		strings.add("java.sql");
		strings.add("java.persistence");
		strings.add("java.transaction");
		return strings;
	}
}
