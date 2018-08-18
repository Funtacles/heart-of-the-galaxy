package com.lilithsthrone.game.character.quests;

import com.lilithsthrone.utils.TreeNode;

/**
 * @since 0.1.99
 * @version 0.2.10
 * @author Innoxia
 */
public class QuestTree {

	public static TreeNode<Quest> mainQuestTree = new TreeNode<Quest>(Quest.MAIN_PROLOGUE);
	public static TreeNode<Quest> enchantmentTree = new TreeNode<Quest>(Quest.SIDE_ENCHANTMENTS_LILAYA_HELP);
	public static TreeNode<Quest> pregnancyTree = new TreeNode<Quest>(Quest.SIDE_PREGNANCY_CONSULT_LILAYA);
	public static TreeNode<Quest> slaveryTree = new TreeNode<Quest>(Quest.SIDE_SLAVER_NEED_RECOMMENDATION);
	public static TreeNode<Quest> accommodationTree = new TreeNode<Quest>(Quest.SIDE_ACCOMMODATION_NEED_LILAYAS_PERMISSION);
	public static TreeNode<Quest> hypnoWatchTree = new TreeNode<Quest>(Quest.SIDE_HYPNO_WATCH_VICKY);
	
	static {
		TreeNode<Quest> node1 = new TreeNode<Quest>(Quest.MAIN_1_A_LILAYAS_TESTS);
		mainQuestTree.addChild(node1);
		TreeNode<Quest> node2 = new TreeNode<Quest>(Quest.MAIN_1_B_DEMON_HOME);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.MAIN_1_C_WOLFS_DEN);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.MAIN_1_D_SLAVERY);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.MAIN_1_E_REPORT_TO_ALEXA);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.MAIN_1_F_SCARLETTS_FATE);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.MAIN_1_G_SLAVERY);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.MAIN_1_H_THE_GREAT_ESCAPE);
		node1.addChild(node2);
		node1 = new TreeNode<Quest>(Quest.MAIN_1_I_ARTHURS_TALE);
		node2.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.MAIN_1_J_ARTHURS_ROOM);
		node1.addChild(node2);

		enchantmentTree.addChild(new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE));

		node1 = new TreeNode<Quest>(Quest.SIDE_PREGNANCY_LILAYA_THE_MIDWIFE);
		pregnancyTree.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);

		accommodationTree.addChild(new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE));
		
		node1 = new TreeNode<Quest>(Quest.SIDE_SLAVER_RECOMMENDATION_OBTAINED);
		slaveryTree.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);
		
		node1 = new TreeNode<Quest>(Quest.SIDE_HYPNO_WATCH_TEST_SUBJECT);
		hypnoWatchTree.addChild(node1);
		node2 = new TreeNode<Quest>(Quest.SIDE_UTIL_COMPLETE);
		node1.addChild(node2);
	}
}
