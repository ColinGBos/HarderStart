package com.vapourdrive.harderstart.items;

import com.vapourdrive.harderstart.HarderStart;

public class ItemRubble extends LogiclessItem
{
	/**
	 * basically the same thing as logicless item expect with different creative tab
	 * @param Name for the texture and the unlocalized name
	 */
	public ItemRubble(String Name)
	{
		super(Name);
		this.setCreativeTab(HarderStart.tabRubble);
	}

}
