/*
 * 
 * Copyright (C) 2004-2009, see @author in JavaDoc for the author 
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 *
 * For contact information please visit http://woped.ba-karlsruhe.de
 *
 */
package org.woped.editor.controller;


import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.Autoscroll;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceContext;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.woped.core.model.AbstractElementModel;
import org.woped.core.model.PetriNetModelProcessor;
import org.woped.core.model.petrinet.AbstractPetriNetModelElement;
import org.woped.core.model.petrinet.ResourceClassModel;
import org.woped.core.model.petrinet.ResourceModel;
import org.woped.core.model.petrinet.TransitionModel;
import org.woped.core.utilities.SwingUtils;
import org.woped.editor.controller.vc.EditorVC;
import org.woped.translations.Messages;



/**
 * @author Tobias Rehnig, Stephanie Obergfell, Jenny Braun, Linda Linsenbolz
 */

@SuppressWarnings("serial")
public class PetriNetResourceEditor extends JPanel 
{
	
	private JPanel				   borderPanel						= null;
	
	// Objects-Panel
	private JPanel 				   objectsPanel						= null;
	private JPanel                 objectsButtonPanel  				= null;
	private JScrollPane			   objectsScrollPane				= null;
	
	private JButton				   objectsNewButton					= null;
	private JButton				   objectsEditButton				= null;
	private JButton				   objectsDeleteButton				= null;
	private JButton			  	   objectsCollapseButton			= null;
	private JButton			  	   objectsExpandButton  			= null;

	private JTree				   objectsTree						= null;
	private DefaultTreeModel 	   objectsTreeModel					= null;

	private DefaultMutableTreeNode objectsTopNode					= null;
	private DefaultMutableTreeNode objectsUnassignedNode			= null;
	private DefaultMutableTreeNode objectsAssignedNode				= null;

	private DefaultListModel       objectsAssignedListModel    	 	= new DefaultListModel();
	private DefaultListModel       objectsUnassignedListModel   	= new DefaultListModel();
	
	//Roles-Panel
	private JPanel                 rolesContentPanel           		= null;
	private JPanel                 rolesPanel            			= null;
	private JPanel                 superRolesPanel         			= null;
	private JPanel                 rolesButtonPanel     			= null;
	private JScrollPane            rolesScrollPane      			= null;
	private JButton				   rolesNewButton					= null;
	private JButton				   rolesEditButton					= null;
	private JButton				   rolesDeleteButton				= null;

	private JButton			  	   rolesCollapseButton				= null;
	private JButton			  	   rolesExpandButton  				= null;

	private JTree				   rolesTree						= null;
	private DefaultTreeModel 	   rolesTreeModel					= null;
	private DefaultMutableTreeNode rolesTopNode						= null;
	private DefaultListModel       rolesListModel               	= new DefaultListModel();

	//	Super-Roles Panel
	private JPanel                 superRolesButtonPanel			= null;
	private JScrollPane            superRolesScrollPane 			= null;
	private JButton				   superRolesNewButton				= null;
	private JButton				   superRolesEditButton				= null;
	private JButton				   superRolesDeleteButton			= null;

	private JButton			  	   superRolesCollapseButton			= null;
	private JButton			  	   superRolesExpandButton  			= null;

	private JTree				   superRolesTree					= null;
	private DefaultTreeModel 	   superRolesTreeModel				= null;
	private DefaultMutableTreeNode superRolesTopNode				= null;
	private DefaultListModel       superRolesListModel          	= new DefaultListModel();

	//	Groups-Panel
	private JPanel                 groupsContentPanel   			= null;
	private JPanel                 groupsPanel        				= null;
	private JPanel                 superGroupsPanel        			= null;
	private JPanel                 groupsButtonPanel    			= null;
	private JScrollPane            groupsScrollPane     			= null;
	private JButton				   groupsNewButton					= null;
	private JButton				   groupsEditButton					= null;
	private JButton				   groupsDeleteButton				= null;

	private JButton			  	   groupsCollapseButton				= null;
	private JButton			  	   groupsExpandButton  				= null;

	private JTree				   groupsTree						= null;
	private DefaultTreeModel 	   groupsTreeModel					= null;
	private DefaultMutableTreeNode groupsTopNode					= null;
	private DefaultListModel       groupsListModel              	= new DefaultListModel();
	
	//	Super-Group Panel
	private JPanel                 superGroupsButtonPanel    		= null;
	private JScrollPane            superGroupsScrollPane			= null;
	private JButton				   superGroupsNewButton				= null;
	private JButton				   superGroupsEditButton			= null;
	private JButton				   superGroupsDeleteButton			= null;

	private JButton			  	   superGroupsCollapseButton		= null;
	private JButton			  	   superGroupsExpandButton  		= null;

	private JTree				   superGroupsTree					= null;
	private DefaultTreeModel 	   superGroupsTreeModel				= null;
	private DefaultMutableTreeNode superGroupsTopNode				= null;
	private DefaultListModel       superGroupsListModel             = new DefaultListModel();
	  


	// Graphic-Panel
	private JPanel					graphicPanel					= null;


	//  Dialog-Frame to create new Super-Resource
	private JFrame				   	dialogFrame						= null;
	private JButton 			  	dialogFrameConfirmButton		= null;
	private	JButton 			 	dialogFrameCancelButton			= null;
  	private JTextField			   	dialogFrameTextField			= null;	
  	private JList				   	selectedRolesList				= null;
  	private JList				   	selectedGroupsList				= null;
  	 
  	
	private ActionListener		   	createResource					= new createResource();
	private ActionListener		   	removeResource					= new removeResource();
	private ActionListener		   	editResource					= new editResource();

	private ActionListener  	   	createSuperResourceFrame		= new createSuperResourceFrame();
	private ActionListener  	   	editSuperResourceFrame			= new editSuperResourceFrame();
	private ActionListener  	   	createSuperResource				= new createSuperResource();
	private ActionListener  	   	editSuperResource				= new editSuperResource();
	private ActionListener  	   	removeSuperResource				= new removeSuperResource();

	private ActionListener		   	expandButtonListener			= new expandButtonListener();
	private ActionListener		   	collapseButtonListener			= new collapseButtonListener();
	private MyTreeExpansionListener treeListener					= new MyTreeExpansionListener();

	private TreeSelectionListener  	treeSelection					= new MyTreeSelectionListener();
	
	//ImageIcon used in the TreeCellRenderer-Classes  
	private ImageIcon 			   	resourceClass  					= Messages.getImageIcon("PetriNet.Resources.ResourceClass");
	private ImageIcon 			   	resourceSuperClass				= Messages.getImageIcon("PetriNet.Resources.ResourceSuperClass");		 				
	private ImageIcon 			   	object		 					= Messages.getImageIcon("PetriNet.Resources.Object");
	private ImageIcon 			   	unassignedIcon  				= Messages.getImageIcon("PetriNet.Resources.Unassigned");

	
	private static final int ITALIC = 0;
	private Font Nodes = new Font("Nodes",ITALIC,14);
	
    private PetriNetModelProcessor petrinet;
    private EditorVC               editor;
    	
	 public EditorVC getEditor()
	    {
	        return editor;
	    }
	 public PetriNetModelProcessor getPetrinet()
	    {
	        return petrinet;
	    }
	 
	 public PetriNetResourceEditor (EditorVC editor)
	    {
	        this.editor = editor;
	        this.petrinet = (PetriNetModelProcessor) editor.getModelProcessor();
	        initialize();
	        reset();
	    }
	 
	 public void reset()
	 {
		 	refreshFromModel(); 
	  }
	  

	    
	   private void initialize(){
	        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
	        this.setSize(new Dimension(1024, 800));
	        this.setLayout(new GridBagLayout());
	        this.add(getBorderPanel());

	        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
	        renderer.setLeafIcon(Messages.getImageIcon("PetriNet.Resources.Unassign.Icon"));
	        renderer.setOpenIcon(Messages.getImageIcon("PetriNet.Resources.Unassign.Icon"));
	   }


	   private JPanel getBorderPanel(){
		   if(borderPanel == null){
			   borderPanel = new JPanel(new GridBagLayout());
			   borderPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			   SwingUtils.setFixedSize(borderPanel,800,600);
			   GridBagConstraints c = new GridBagConstraints();

		        c.weightx = 4;
		        c.weighty = 0;
		        

		        c.anchor = GridBagConstraints.WEST;
		        c.gridx = 0;
		        c.gridy = 0;
		        this.add(getObjectsContentPanel(),c);
		        
		        c.anchor = GridBagConstraints.WEST;
		        c.gridx = 1;
		        c.gridy = 0;
		        this.add(getGraphicPanel(),c);

		        c.anchor = GridBagConstraints.EAST;
		        c.gridx = 2;
		        c.gridy = 0;
		        this.add(getRolesContentPanel(),c);
		       
		        c.anchor = GridBagConstraints.WEST;
		     	c.gridx = 3;
		        c.gridy = 0;
		        this.add(getGroupsContentPanel(),c);
		   }
		   return borderPanel;
	   }
	   
   
	   
	   
//		*****************OBJECT_PANEL*************************************
//	 	*****************************************************************
	   private JPanel getObjectsContentPanel(){
		   if (objectsPanel == null){
			   objectsPanel = new JPanel(new GridBagLayout());
			   objectsPanel.setBorder(BorderFactory
	                    .createCompoundBorder(BorderFactory.createTitledBorder(
	                    		Messages.getString("PetriNet.Resources.Resource")), 
	                    		BorderFactory.createEmptyBorder()));


	            SwingUtils.setFixedSize(objectsPanel,300,580);

	            GridBagConstraints c = new GridBagConstraints();
	            c.weightx = 1;
		        c.weighty = 1;
		        c.anchor = GridBagConstraints.NORTH;

		        c.gridx = 0;
		        c.gridy = 0;
		        objectsPanel.add(getObjectsButtonPanel(),c);
	            
		        c.fill = GridBagConstraints.VERTICAL;
		        c.gridx = 0;
		        c.gridy = 1;

		        objectsPanel.add(getObjectsScrollPane(),c);
		   }
		   return objectsPanel;
	   }
	   
//****************************OBJECTS_BUTTON_PANEL********************************   
	   private JPanel getObjectsButtonPanel(){
		   if (objectsButtonPanel == null){
			   objectsButtonPanel = new JPanel(new GridLayout());
			   SwingUtils.setFixedSize(objectsButtonPanel,300,23);
			   objectsButtonPanel.add(getObjectsNewButton());
			   objectsButtonPanel.add(getObjectsEditButton());
			   objectsButtonPanel.add(getObjectsDeleteButton());
			   objectsButtonPanel.add(getObjectsExpandButton());
			   objectsButtonPanel.add(getObjectsCollapseButton());
		   }
		   return objectsButtonPanel;
	   }
	   
	   
	   private JButton getObjectsNewButton(){
	        if (objectsNewButton == null){
	        	objectsNewButton = new JButton();
	        	objectsNewButton.setIcon(Messages.getImageIcon("PetriNet.Resources.New"));
//	        	objectsNewButton.setText(Messages.getString("PetriNet.Resources.New.Title"));
	        	objectsNewButton.setToolTipText(Messages.getString("PetriNet.Resources.New.Title"));
	        	objectsNewButton.addActionListener(createResource);
	        }

	        return objectsNewButton;
	    }
	   
	   private JButton getObjectsEditButton(){
	        if (objectsEditButton == null){
	        	objectsEditButton = new JButton();
	        	objectsEditButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Edit"));
//	        	objectsEditButton.setText(Messages.getString("PetriNet.Resources.Edit.Title"));
	        	objectsEditButton.setToolTipText(Messages.getString("Button.Edit.Title"));
	        	objectsEditButton.setEnabled(true);
	        	objectsEditButton.addActionListener(editResource);
	        }

	        return objectsEditButton;
	    }
	   
	   private JButton getObjectsDeleteButton(){
	        if (objectsDeleteButton == null){
	        	objectsDeleteButton = new JButton();
	        	objectsDeleteButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Delete"));
//	        	objectsDeleteButton.setText(Messages.getString("PetriNet.Resources.Delete.Title"));
	        	objectsDeleteButton.setToolTipText(Messages.getString("PetriNet.Resources.Delete.Title"));
	        	objectsDeleteButton.addActionListener(removeResource);
	        	objectsDeleteButton.setEnabled(true);
	        	
	        }

	        return objectsDeleteButton;
	    }
	   
	   private JButton getObjectsExpandButton(){
	        if (objectsExpandButton == null){
	        	objectsExpandButton = new JButton();
	        	objectsExpandButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Expand"));
	        	objectsExpandButton.setToolTipText(Messages.getString("PetriNet.Resources.Expand.Title"));
	        	objectsExpandButton.addActionListener(expandButtonListener);
	        }

	        return objectsExpandButton;
	    }
	   
	   private JButton getObjectsCollapseButton(){
	        if (objectsCollapseButton == null){
	        	objectsCollapseButton = new JButton();
	        	objectsCollapseButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Collapse"));
	        	objectsCollapseButton.setToolTipText(Messages.getString("PetriNet.Resources.Collapse.Title"));
	        	objectsCollapseButton.addActionListener(collapseButtonListener);
	        	objectsCollapseButton.setEnabled(false);
	        }

	        return objectsCollapseButton;
	   }
	   
//	   *********************OBJECTS_CONTENT_PANEL**********************************

	   private JScrollPane getObjectsScrollPane(){
		   if (objectsScrollPane == null){
			   objectsScrollPane = new JScrollPane(getObjectsTree());
			   objectsScrollPane.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));


			   SwingUtils.setFixedSize(objectsScrollPane, 300,530);

		   }
		   return objectsScrollPane;
	   }
	   
	   
	  private DragTree getObjectsTree(){
		   if (objectsTree == null){
			    objectsTopNode = new DefaultMutableTreeNode("Objects");
			    objectsTreeModel = new DefaultTreeModel(objectsTopNode);		    
			    objectsUnassignedNode = new DefaultMutableTreeNode(
			    		Messages.getString("PetriNet.Resources.Resource.Unassigned"));
			    objectsTopNode.add(objectsUnassignedNode);
			    objectsAssignedNode = new DefaultMutableTreeNode(
			    		Messages.getString("PetriNet.Resources.Resource.Assigned"));
			    objectsTopNode.add(objectsAssignedNode);			
			    objectsTree = new DragTree(objectsTopNode,objectsAssignedNode);			    
			    objectsTree.setRootVisible(false);
			    objectsTree.setRowHeight(20);
			    objectsTree.setEditable(false);
			    objectsTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
			    objectsTree.addTreeSelectionListener(new MyTreeSelectionListener());
			    objectsTree.addTreeExpansionListener(treeListener);
			    objectsTree.setShowsRootHandles(true);
			    objectsTree.setFont(Nodes);	
			    objectsTree.setShowsRootHandles(true);
			    objectsTree.setCellRenderer(rendererResourceClass);

			    
		   }return (DragTree)objectsTree;
	   }
	  
	
//*******************************GRAPHIC_PANEL*******************************
//***********************************************************************	 
	  private JPanel getGraphicPanel(){
		  if (graphicPanel == null){
			  graphicPanel = new JPanel(new GridBagLayout());



			  SwingUtils.setFixedSize(graphicPanel,60,200);

	            
	            JLabel graphic1 = new JLabel(), graphic2 = new JLabel();
	            
	            graphic1.setIcon(Messages.getImageIcon("PetriNet.Resources.Mouse"));
	            graphic1.setToolTipText("Drag&Drop");
	            graphic2.setIcon(Messages.getImageIcon("PetriNet.Resources.Drag"));
	            graphic2.setToolTipText("Drag&Drop");
	           	            
	            GridBagConstraints c = new GridBagConstraints();
	            c.weightx = 2;
		        c.weighty = 2;
		        c.anchor = GridBagConstraints.NORTHEAST;
		        c.gridx = 0;
		        c.gridy = 0;
		        graphicPanel.add(graphic1, c);
		        c.anchor = GridBagConstraints.NORTHWEST;
		        c.gridx = 1;
		        c.gridy = 0;
		        graphicPanel.add(graphic2, c);
		  }
		  
		  return graphicPanel;
	  }
	  

	  
	// *******************************ROLES_PANEL********************************************  
	// ***********************************************************
	   private JPanel getRolesContentPanel(){
		   if (rolesContentPanel  == null){
			   rolesContentPanel  = new JPanel(new GridBagLayout());

	            SwingUtils.setFixedSize(rolesContentPanel , 300,580);
	          
	            GridBagConstraints c = new GridBagConstraints();
	            
		        c.weightx = 1;
		        c.weighty = 1;
		        c.anchor = GridBagConstraints.NORTH;
	           
	            c.gridx = 0;
		        c.gridy = 0;
		        rolesContentPanel.add(getRolesPanel(),c);
                     
		        c.fill = GridBagConstraints.VERTICAL;
	            c.gridx = 0;
	            c.gridy = 1;
	            rolesContentPanel.add(getSuperRolesPanel(),c);
		   }
		   return rolesContentPanel ;
	   }

//****************************ROLES_PANEL and SUPER_ROLES_PANEL with Border ********************************
	   private JPanel getRolesPanel(){
		   if (rolesPanel  == null){
			   rolesPanel  = new JPanel(new GridBagLayout());
	            rolesPanel .setBorder(BorderFactory
	                    .createCompoundBorder(BorderFactory.createTitledBorder(
	                    		Messages.getString("PetriNet.Resources.Roles")), 
	                    		BorderFactory.createEmptyBorder()));
	            SwingUtils.setFixedSize(rolesPanel , 300,395);
	            
	            GridBagConstraints c = new GridBagConstraints();
	            
		        c.weightx = 1;
		        c.weighty = 1;
		        c.anchor = GridBagConstraints.NORTH;
		        c.gridx = 0;
		        c.gridy = 0;
		        rolesPanel .add(getRolesButtonPanel(),c);
		        
		        c.fill = GridBagConstraints.VERTICAL;
	            c.gridx = 0;
	            c.gridy = 1;
	            rolesPanel .add(getRolesScrollPane(),c);
		   }
		   return rolesPanel;
	   }
	   
	   private JPanel getSuperRolesPanel(){
		   if (superRolesPanel == null){
			   superRolesPanel = new JPanel(new GridBagLayout());
			   superRolesPanel.setBorder(BorderFactory
	                    .createCompoundBorder(BorderFactory.createTitledBorder(
	                    		Messages.getString("PetriNet.Resources.SuperRoles")), 
	                    		BorderFactory.createEmptyBorder()));
	            SwingUtils.setFixedSize(superRolesPanel, 300,180);
	          
	            GridBagConstraints c = new GridBagConstraints();
	            
		        c.weightx = 1;
		        c.weighty = 1;
		        c.anchor = GridBagConstraints.NORTH;
		        c.gridx = 0;
		        c.gridy = 0;
		        superRolesPanel.add(getSuperRolesButtonPanel(),c);
		        
		        c.fill = GridBagConstraints.VERTICAL;
	            c.gridx = 0;
	            c.gridy = 1;
	            superRolesPanel.add(getSuperRolesScrollPane(),c);
		   }
		   return superRolesPanel;
	   }
	   

//****************************ROLES_BUTTON_PANEL*******************************	   
	   private JPanel getRolesButtonPanel(){
		   if (rolesButtonPanel == null){
			   rolesButtonPanel = new JPanel(new GridLayout());
			   SwingUtils.setFixedSize(rolesButtonPanel, 300,23);
			   rolesButtonPanel.add(getRolesNewButton());
			   rolesButtonPanel.add(getRolesEditButton());
			   rolesButtonPanel.add(getRolesDeleteButton());
			   rolesButtonPanel.add(getRolesExpandButton());
			   rolesButtonPanel.add(getRolesCollapseButton());
		   }
		   return rolesButtonPanel;
	   }
	   
	   
	   private JButton getRolesNewButton(){
	        if (rolesNewButton == null){
	        	rolesNewButton = new JButton();
	        	rolesNewButton.setIcon(Messages.getImageIcon("PetriNet.Resources.New"));
	        	rolesNewButton.setToolTipText(Messages.getString("PetriNet.Resources.New.Title"));
	            rolesNewButton.addActionListener(createResource);
	        }

	        return rolesNewButton;
	    }
	   
	   private JButton getRolesEditButton(){


	        if (rolesEditButton == null){
	        	rolesEditButton = new JButton();
	        	rolesEditButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Edit"));
	        	rolesEditButton.setToolTipText(Messages.getString("Button.Edit.Title"));
	        	rolesEditButton.setEnabled(false);
	        	rolesEditButton.addActionListener(editResource);

	        }

	        return rolesEditButton;
	    }
	   
	   private JButton getRolesDeleteButton(){
	        if (rolesDeleteButton == null){
	        	rolesDeleteButton = new JButton();
	        	rolesDeleteButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Delete"));
	        	rolesDeleteButton.setToolTipText(Messages.getString("PetriNet.Resources.Delete.Title"));
	        	rolesDeleteButton.addActionListener(removeResource);
	        	rolesDeleteButton.setEnabled(false);
	        }

	        return rolesDeleteButton;
	    }
	   
	   private JButton getRolesExpandButton(){
	        if (rolesExpandButton == null){
	        	rolesExpandButton = new JButton();
	        	rolesExpandButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Expand"));
	        	rolesExpandButton.setToolTipText(Messages.getString("PetriNet.Resources.Expand.Title"));
	        	rolesExpandButton.addActionListener(expandButtonListener);
	        }

	        return rolesExpandButton;
	    }
	   
	   private JButton getRolesCollapseButton(){
	        if (rolesCollapseButton == null){
	        	rolesCollapseButton = new JButton();
	        	rolesCollapseButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Collapse"));
	        	rolesCollapseButton.setToolTipText(Messages.getString("PetriNet.Resources.Collapse.Title"));
	        	rolesCollapseButton.addActionListener(collapseButtonListener);
	        	rolesCollapseButton.setEnabled(false);
	        }

	        return rolesCollapseButton;
	    }
	   
//	   *********************ROLES_CONTENT_PANEL**************************
	
	   private JScrollPane getRolesScrollPane(){
		   if (rolesScrollPane == null){
			   rolesScrollPane = new JScrollPane(getRolesTree());
			   rolesScrollPane.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));


			   SwingUtils.setFixedSize(rolesScrollPane, 300,345);



		   }
		   return rolesScrollPane;
	   }
	  	   
	   private DropTree getRolesTree(){
		   if (rolesTree == null){
			    rolesTopNode = new DefaultMutableTreeNode("Roles");
			    rolesTree = new DropTree(rolesTopNode,getPetrinet());
			    rolesTreeModel = new DefaultTreeModel(rolesTopNode);
			    rolesTree.setRowHeight(20);
			    rolesTree.setEditable(false);
			    rolesTree.setRootVisible(false);
			    rolesTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
			    rolesTree.setShowsRootHandles(true);
			    rolesTree.setFont(Nodes);  
			    rolesTree.setFont(Nodes);
			    rolesTree.addTreeSelectionListener(treeSelection);
			    rolesTree.addTreeExpansionListener(treeListener);
			    rolesTree.setCellRenderer(rendererResourceClass);

		   }return (DropTree)rolesTree;
	   }
	   
// Drop-Target-Listener:
	   DropTargetListener MyDropTargetListener =
		   new DropTargetListener() {

		    // Die Maus betritt die Komponente mit
		    // einem Objekt
		    public void dragEnter(DropTargetDragEvent e) {
		    	
		    }
		    	
		    // Die Komponente wird verlassen 
		    public void dragExit(DropTargetEvent e) {
		    	
		    }

		    // Die Maus bewegt sich �ber die Komponente
		    public void dragOver(DropTargetDragEvent e) {}

		    public void drop(DropTargetDropEvent e) {
		      try {
		        Transferable tr = e.getTransferable();
		        DataFlavor[] flavors = tr.getTransferDataFlavors();
		        for (int i = 0; i < flavors.length; i++)
		         if (flavors[i].isFlavorJavaFileListType()) {
		          // Zun�chst annehmen
		          e.acceptDrop(e.getDropAction());
//		          List files = (List) tr.getTransferData(flavors[i]);
		          
		          e.dropComplete(true);
		          
		          return;
		         }
		        		
		      } catch (Throwable t) { t.printStackTrace(); }
		      // Ein Problem ist aufgetreten
		      e.rejectDrop();
		    }
		    
		    
		    
		     
		    // Jemand hat die Art des Drops (Move, Copy, Link)
		    // ge�ndert
		    public void dropActionChanged(
		           DropTargetDragEvent e) {}
		  };
		  
	   //ENDE Listener
		  

//			************************SUPER_ROLES_BUTTON_PANEL***************
		  private JPanel getSuperRolesButtonPanel(){
			   if (superRolesButtonPanel == null){
				   superRolesButtonPanel = new JPanel(new GridLayout());
				   SwingUtils.setFixedSize(superRolesButtonPanel, 300,23);
				   superRolesButtonPanel.add(getSuperRolesNewButton());
				   superRolesButtonPanel.add(getSuperRolesEditButton());
				   superRolesButtonPanel.add(getSuperRolesDeleteButton());
				   superRolesButtonPanel.add(getSuperRolesExpandButton());
				   superRolesButtonPanel.add(getSuperRolesCollapseButton());
			   }
			   return superRolesButtonPanel;
		   }
		   
		   
		   private JButton getSuperRolesNewButton(){
		        if (superRolesNewButton == null){
		        	superRolesNewButton = new JButton();
		        	superRolesNewButton.setIcon(Messages.getImageIcon("PetriNet.Resources.New"));
		        	superRolesNewButton.setToolTipText(Messages.getString("PetriNet.Resources.New.Title"));
		            superRolesNewButton.addActionListener(createSuperResourceFrame);
		        }

		        return superRolesNewButton;
		    }
		   
		   private JButton getSuperRolesEditButton(){
		        if (superRolesEditButton == null){
		        	superRolesEditButton = new JButton();
		        	superRolesEditButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Edit"));
		        	superRolesEditButton.setToolTipText(Messages.getString("Button.Edit.Title"));
		        	superRolesEditButton.setEnabled(false);
		        	superRolesEditButton.addActionListener(editSuperResourceFrame);
		        }

		        return superRolesEditButton;
		    }
		   
		   private JButton getSuperRolesDeleteButton(){
		        if (superRolesDeleteButton == null){
		        	superRolesDeleteButton = new JButton();
		        	superRolesDeleteButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Delete"));
		        	superRolesDeleteButton.setToolTipText(Messages.getString("PetriNet.Resources.Delete.Title"));
		        	superRolesDeleteButton.addActionListener(removeSuperResource);
		        	superRolesDeleteButton.setEnabled(false);
		        }

		        return superRolesDeleteButton;
		    }


		   
		   private JButton getSuperRolesExpandButton(){
		        if (superRolesExpandButton == null){
		        	superRolesExpandButton = new JButton();
		        	superRolesExpandButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Expand"));
		        	superRolesExpandButton.setToolTipText(Messages.getString("PetriNet.Resources.Expand.Title"));
		        	superRolesExpandButton.addActionListener(expandButtonListener);
		        }

		        return superRolesExpandButton;
		    }
		   
		   private JButton getSuperRolesCollapseButton(){
		        if (superRolesCollapseButton == null){
		        	superRolesCollapseButton = new JButton();
		        	superRolesCollapseButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Collapse"));
		        	superRolesCollapseButton.setToolTipText(Messages.getString("PetriNet.Resources.Collapse.Title"));
		        	superRolesCollapseButton.addActionListener(collapseButtonListener);
		        	superRolesCollapseButton.setEnabled(false);
		        }

		        return superRolesCollapseButton;
		    }



		  
//        ***************************SUPER_ROLES_CONTENT_PANEL********************

	          private JScrollPane getSuperRolesScrollPane(){
	              if (superRolesScrollPane == null){
	                   superRolesScrollPane = new JScrollPane(getSuperRolesTree());
	                   superRolesScrollPane.setBorder(BorderFactory.createEtchedBorder(new Color (141, 182, 205), new Color (132, 112, 255)));
	                   superRolesScrollPane.setBackground(new Color (2, 12, 241));
	                   SwingUtils.setFixedSize(superRolesScrollPane, 300,135);

	               }
	               return superRolesScrollPane;
	           }

	          private JTree getSuperRolesTree(){
	               if (superRolesTree == null){
	                    superRolesTopNode = new DefaultMutableTreeNode("Super-Roles");
	                    superRolesTree = new JTree(superRolesTopNode);
	                    superRolesTreeModel = new DefaultTreeModel(superRolesTopNode);
	                    superRolesTree.setRowHeight(20);
	                    superRolesTree.setEditable(false);
	                    superRolesTree.setRootVisible(false);
	                    superRolesTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	                    superRolesTree.setShowsRootHandles(true);
	                    superRolesTree.setFont(Nodes);
	                    superRolesTree.addTreeExpansionListener(treeListener);
	                    superRolesTree.addTreeSelectionListener(treeSelection);
	                    superRolesTree.setCellRenderer(rendererResourceSuperClass);
	               }return (JTree)superRolesTree;
	           }
	   
//	   **************************GROUPS_PANEL****************************
//	   *****************************************************************
	   private JPanel getGroupsContentPanel(){
		   if (groupsContentPanel == null){
			   groupsContentPanel = new JPanel(new GridBagLayout());
	            SwingUtils.setFixedSize(groupsContentPanel, 300,580);
	            GridBagConstraints c = new GridBagConstraints();
	            
		        c.weightx = 1;
		        c.weighty = 1;
		        c.anchor = GridBagConstraints.NORTH;

				c.gridx = 0;
		        c.gridy = 0;
		        groupsContentPanel.add(getGroupsPanel(),c);
                     
		        c.fill = GridBagConstraints.VERTICAL;
	            c.gridx = 0;
	            c.gridy = 1;
	            groupsContentPanel.add(getSuperGroupsPanel(),c);
		   }
		   return groupsContentPanel;
	   }
	   
 //****************************GROUPS_PANEL and SUPER_GROUPS_PANEL with Border ********************************
	   private JPanel getGroupsPanel(){
		   if (groupsPanel == null){
			   groupsPanel = new JPanel(new GridBagLayout());
			   groupsPanel.setBorder(BorderFactory
	                    .createCompoundBorder(BorderFactory.createTitledBorder(Messages.getString("PetriNet.Resources.Groups")), BorderFactory.createEmptyBorder()));
	            SwingUtils.setFixedSize(groupsPanel , 300,395);
	          
	            GridBagConstraints c = new GridBagConstraints();
	            
		        c.weightx = 1;
		        c.weighty = 1;
		        c.anchor = GridBagConstraints.NORTH;
		        c.gridx = 0;
		        c.gridy = 0;
		        groupsPanel.add(getGroupsButtonPanel(),c);
		        
		        c.fill = GridBagConstraints.VERTICAL;
	            c.gridx = 0;
	            c.gridy = 1;
	            groupsPanel.add(getGroupsScrollPane(),c);
		   }
		   return groupsPanel;
	   }
	   
	   private JPanel getSuperGroupsPanel(){
		   if (superGroupsPanel == null){
			   superGroupsPanel = new JPanel(new GridBagLayout());
			   superGroupsPanel.setBorder(BorderFactory
	                    .createCompoundBorder(BorderFactory.createTitledBorder(Messages.getString("PetriNet.Resources.SuperGroups")), BorderFactory.createEmptyBorder()));
	            SwingUtils.setFixedSize(superGroupsPanel, 300,180);
	          
	            GridBagConstraints c = new GridBagConstraints();
	            
		        c.weightx = 1;
		        c.weighty = 1;
		        c.anchor = GridBagConstraints.NORTH;
		        c.gridx = 0;
		        c.gridy = 0;
		        superGroupsPanel.add(getSuperGroupsButtonPanel(),c);
		        
		        c.fill = GridBagConstraints.VERTICAL;
	            c.gridx = 0;
	            c.gridy = 1;
	            superGroupsPanel.add(getSuperGroupsScrollPane(),c);
		   }
		   return superGroupsPanel;
	   }
	   
	   
//	   *********************GROUPS_CONTENT_PANEL**************************

	   private JScrollPane getGroupsScrollPane(){
		   if (groupsScrollPane == null){
			   groupsScrollPane = new JScrollPane(getGroupsTree());
			   groupsScrollPane.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.GRAY));
			   SwingUtils.setFixedSize(groupsScrollPane, 300,345);
			   

		   }
		   return groupsScrollPane;
	   }
	   private DropTree getGroupsTree(){
		   if (groupsTree == null){
			    groupsTopNode = new DefaultMutableTreeNode("Groups");
			    groupsTree = new DropTree(groupsTopNode,getPetrinet());
			    groupsTreeModel = new DefaultTreeModel(groupsTopNode);
			    groupsTree.setRowHeight(20);
			    groupsTree.setRootVisible(false);
			    groupsTree.setEditable(false);
			    groupsTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
			    groupsTree.setShowsRootHandles(true);
			    groupsTree.setFont(Nodes);	
			    groupsTree.addTreeExpansionListener(treeListener);
			    groupsTree.addTreeSelectionListener(treeSelection);
		        groupsTree.setCellRenderer(rendererResourceClass);
		        groupsTree.updateUI();

		   }return (DropTree)groupsTree;
	   }
	   

	   
	 //****************************GROUPS_BUTTON_PANEL********************************	   
	   private JPanel getGroupsButtonPanel(){
		   if (groupsButtonPanel == null){
			   groupsButtonPanel = new JPanel(new GridLayout());
			   SwingUtils.setFixedSize(groupsButtonPanel, 300,23);
			   groupsButtonPanel.add(getGroupsNewButton());
			   groupsButtonPanel.add(getGroupsEditButton());
			   groupsButtonPanel.add(getGroupsDeleteButton());
			   groupsButtonPanel.add(getGroupsExpandButton());
			   groupsButtonPanel.add(getGroupsCollapseButton());
		   }
		   return groupsButtonPanel;
	   }
	   
	   
	   private JButton getGroupsNewButton(){
	        if (groupsNewButton == null){
	        	groupsNewButton = new JButton();
	        	groupsNewButton.setIcon(Messages.getImageIcon("PetriNet.Resources.New"));
	        	groupsNewButton.setToolTipText(Messages.getString("PetriNet.Resources.New.Title"));
	        	groupsNewButton.addActionListener(createResource);
	        }

	        return groupsNewButton;
	    }
	   
	   private JButton getGroupsEditButton(){
	        if (groupsEditButton == null){
	        	groupsEditButton = new JButton();
	        	groupsEditButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Edit"));
	        	groupsEditButton.setToolTipText(Messages.getString("Button.Edit.Title"));
	        	groupsEditButton.setEnabled(false);
	        	groupsEditButton.addActionListener(editResource);
	        }

	        return groupsEditButton;
	    }
	   
	   private JButton getGroupsDeleteButton(){
	        if (groupsDeleteButton == null){
	        	groupsDeleteButton = new JButton();
	        	groupsDeleteButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Delete"));
	        	groupsDeleteButton.setToolTipText(Messages.getString("PetriNet.Resources.Delete.Title"));
	        	groupsDeleteButton.addActionListener(removeResource);
	        	groupsDeleteButton.setEnabled(false);
	        }

	        return groupsDeleteButton;
	    }
	   


	   private JButton getGroupsExpandButton(){
	        if (groupsExpandButton == null){
	        	groupsExpandButton = new JButton();
	        	groupsExpandButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Expand"));
	        	groupsExpandButton.setToolTipText(Messages.getString("PetriNet.Resources.Expand.Title"));
	        	groupsExpandButton.addActionListener(expandButtonListener);

	        }

	        return groupsExpandButton;
	    }

	   
	   private JButton getGroupsCollapseButton(){
	        if (groupsCollapseButton == null){
	        	groupsCollapseButton = new JButton();
	        	groupsCollapseButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Collapse"));
	        	groupsCollapseButton.setToolTipText(Messages.getString("PetriNet.Resources.Collapse.Title"));
	        	groupsCollapseButton.addActionListener(collapseButtonListener);
	        	groupsCollapseButton.setEnabled(false);

	        }

	        return groupsCollapseButton;
	    }

//      **********************SUPER_GROUPS_BUTTON_PANEL*****************************

		  private JPanel getSuperGroupsButtonPanel(){
			   if (superGroupsButtonPanel == null){
				   superGroupsButtonPanel = new JPanel(new GridLayout());
				   SwingUtils.setFixedSize(superGroupsButtonPanel, 300,23);
				   superGroupsButtonPanel.add(getSuperGroupsNewButton());
				   superGroupsButtonPanel.add(getSuperGroupsEditButton());
				   superGroupsButtonPanel.add(getSuperGroupsDeleteButton());
				   superGroupsButtonPanel.add(getSuperGroupsExpandButton());
				   superGroupsButtonPanel.add(getSuperGroupsCollapseButton());
			   }
			   return superGroupsButtonPanel;
		   }
		   
		   
		   private JButton getSuperGroupsNewButton(){
		        if (superGroupsNewButton == null){
		        	superGroupsNewButton = new JButton();
		        	superGroupsNewButton.setIcon(Messages.getImageIcon("PetriNet.Resources.New"));
		        	superGroupsNewButton.setToolTipText(Messages.getString("PetriNet.Resources.New.Title"));
		            superGroupsNewButton.addActionListener(createSuperResourceFrame);
		        }

		        return superGroupsNewButton;
		    }
		   
		   private JButton getSuperGroupsEditButton(){
		        if (superGroupsEditButton == null){
		        	superGroupsEditButton = new JButton();
		        	superGroupsEditButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Edit"));
		        	superGroupsEditButton.setToolTipText(Messages.getString("Button.Edit.Title"));
		        	superGroupsEditButton.setEnabled(false);
		        	superGroupsEditButton.addActionListener(editSuperResourceFrame);
		        }

		        return superGroupsEditButton;
		    }
		   
		   private JButton getSuperGroupsDeleteButton(){
		        if (superGroupsDeleteButton == null){
		        	superGroupsDeleteButton = new JButton();
		        	superGroupsDeleteButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Delete"));
		        	superGroupsDeleteButton.setToolTipText(Messages.getString("PetriNet.Resources.Delete.Title"));
		        	superGroupsDeleteButton.addActionListener(removeSuperResource);


		        	superGroupsDeleteButton.setEnabled(false);
		        }

		        return superGroupsDeleteButton;
		    }
		   
		   private JButton getSuperGroupsExpandButton(){
		        if (superGroupsExpandButton == null){
		        	superGroupsExpandButton = new JButton();
		        	superGroupsExpandButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Expand"));
		        	superGroupsExpandButton.setToolTipText(Messages.getString("PetriNet.Resources.Expand.Title"));
		        	superGroupsExpandButton.addActionListener(expandButtonListener);
		        }

		        return superGroupsExpandButton;
		    }
		   
		   private JButton getSuperGroupsCollapseButton(){
		        if (superGroupsCollapseButton == null){
		        	superGroupsCollapseButton = new JButton();
		        	superGroupsCollapseButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Collapse"));
		        	superGroupsCollapseButton.setToolTipText(Messages.getString("PetriNet.Resources.Collapse.Title"));
		        	superGroupsCollapseButton.addActionListener(collapseButtonListener);
		        	superGroupsCollapseButton.setEnabled(false);
		        }

		        return superGroupsCollapseButton;
		    }

		   
//     ***************************SUPER_GROUPS_CONTENT_PANEL************************

	          private JScrollPane getSuperGroupsScrollPane(){
	              if (superGroupsScrollPane == null){
	                   superGroupsScrollPane = new JScrollPane(getSuperGroupsTree());
	                   superGroupsScrollPane.setBorder(BorderFactory.createEtchedBorder(new Color (141, 182, 205), new Color (132, 112, 255)));
	                   superGroupsScrollPane.setBackground(new Color (2, 12, 241));
	                   SwingUtils.setFixedSize(superGroupsScrollPane, 300,135);

	               }
	               return superGroupsScrollPane;
	           }
	          private JTree getSuperGroupsTree(){
	               if (superGroupsTree == null){
	                    superGroupsTopNode = new DefaultMutableTreeNode("Super-Groups");
	                    superGroupsTree = new JTree(superGroupsTopNode);
	                    superGroupsTreeModel = new DefaultTreeModel(superGroupsTopNode);
	                    superGroupsTree.setRowHeight(20);
	                    superGroupsTree.setEditable(false);
	                    superGroupsTree.setRootVisible(false);
	                    superGroupsTree.getSelectionModel().setSelectionMode(
	                    		TreeSelectionModel.SINGLE_TREE_SELECTION);
	                    superGroupsTree.setShowsRootHandles(true);
	                    superGroupsTree.setFont(Nodes);
	                    superGroupsTree.addTreeExpansionListener(treeListener);
	                    superGroupsTree.addTreeSelectionListener(treeSelection);
	                    superGroupsTree.setCellRenderer(rendererResourceSuperClass);
	                 
	               }return (JTree)superGroupsTree;
	           }
	           

	   
	          
	          
	   	   private class createSuperResourceFrame implements ActionListener {
			   	
	   		 public void actionPerformed(ActionEvent e) {
				   try{
					   dialogFrame = new JFrame();
					  if(e.getSource()==superRolesNewButton){
						   dialogFrame.setTitle(Messages.getString("PetriNet.Resources.Resource.CreateSuperRole"));
					   }
					  if(e.getSource()==superGroupsNewButton){
						   dialogFrame.setTitle(Messages.getString("PetriNet.Resources.Resource.CreateSuperGroup")); 
					   }

					   dialogFrame.setSize(500, 400);
					   dialogFrame.setLocationRelativeTo(rolesContentPanel);
					   dialogFrame.setVisible(true);
					   dialogFrame.setResizable(false);

					   dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		   		
					   java.awt.Container c =  dialogFrame.getContentPane();
					   JPanel contentPane = new JPanel(new BorderLayout());
					   contentPane.setBorder(BorderFactory.createEmptyBorder() );
					   contentPane.setBackground(Color.WHITE);
					   SwingUtils.setFixedSize(contentPane,500,400);
					   c.add(contentPane);
					   
					   JPanel namePanel = new JPanel(new GridBagLayout());
					   SwingUtils.setFixedSize(namePanel,500,50);
					   namePanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE, Color.GRAY));
					  
					   		GridBagConstraints a = new GridBagConstraints();
					   		a.weightx = 2;
					   		a.weighty = 1;
					   		JLabel nameLabel = new JLabel(Messages.getString("PetriNet.Resources.ResourceName")+":   ");
					   		dialogFrameTextField = new JTextField();
					   		SwingUtils.setFixedSize(dialogFrameTextField,300,25);
					
					   		a.gridx = 0;
					   		a.gridy = 0;
					   		a.anchor = GridBagConstraints.EAST;
					   		namePanel.add(nameLabel,a);
					 
					   		a.gridx = 1;					   
					   		a.gridy = 0;
					   		a.anchor = GridBagConstraints.WEST;
					   		namePanel.add(dialogFrameTextField,a);
					
					   		contentPane.add(namePanel,BorderLayout.NORTH);

					   JPanel defineResourcePanel = new JPanel(new GridBagLayout());
					   SwingUtils.setFixedSize(defineResourcePanel,500,300);
					   defineResourcePanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE, Color.GRAY));

					   		GridBagConstraints b = new GridBagConstraints();
					   		b.weightx = 1;
					   		b.weighty = 2;
					  
					   		JLabel addRolesLabel = new JLabel(("                         ")+
					   				(Messages.getString("PetriNet.Resources.Resource.AddtoSuperResource"))+(":"));
					   		b.gridx = 0;
					   		b.gridy = 1;
					   		b.anchor = GridBagConstraints.WEST;
					   		defineResourcePanel.add(addRolesLabel,b);
					   
					  
					   		JPanel selectPanel = new JPanel (new GridLayout());
					   		SwingUtils.setFixedSize(selectPanel,300,240);
					   		JScrollPane selectScrollPane = new JScrollPane();
					   		selectScrollPane.setBackground(Color.WHITE);
					   		
					   		SwingUtils.setFixedSize(selectScrollPane,300,260);
//					   		selectPanel.add(selectScrollPane);
					   		
					   		b.gridx = 0;
					   		b.gridy = 2;
					   		b.anchor = GridBagConstraints.CENTER;
					   		defineResourcePanel.add(selectPanel,b);
					   		if(e.getSource()==superRolesNewButton){
					   			if(selectedRolesList==null){
					   				selectedRolesList = new JList(rolesListModel);
					   				}   			
					   				selectedRolesList.setSelectionModel(new ToggleSelectionModel());
					   				selectPanel.add(selectedRolesList);

					   		}
					   		if(e.getSource()==superGroupsNewButton){
					   			selectedGroupsList = new JList (groupsListModel);
					   			selectedGroupsList.setSelectionModel(new ToggleSelectionModel());
					   			selectPanel.add(selectedGroupsList);
					   			
					   		}
					   		if(e.getSource()==superRolesEditButton){
					   			selectedRolesList = new JList(rolesListModel);
					   			selectedRolesList.setSelectionModel(new ToggleSelectionModel());
					   			
					   			SuperRolesTreeNode superRole = 
					   				(SuperRolesTreeNode) superRolesTree.getLastSelectedPathComponent();
					   			dialogFrameTextField.setText(superRole.toString());
					   			for (int i =0;i<superRole.getChildCount();i++){
					   				String currentRoleInTree = superRole.getChildAt(i).toString();
					   				for(int j=0;j < rolesListModel.getSize();j++){
					   					String currentRoleInList = rolesListModel.get(j).toString();
					   					if(currentRoleInTree.equals(currentRoleInList)){
					   						selectedRolesList.setSelectedIndex(j);	
					   					}
					   				}
					   			}
					   			selectPanel.add(selectedRolesList);
					   		}
					   		
					   		if(e.getSource()==superGroupsEditButton){
					   			selectedGroupsList = new JList (groupsListModel);
					   			
					   			selectedGroupsList.setSelectionModel(new ToggleSelectionModel());
					   			SuperGroupsTreeNode superGroup = 
					   				(SuperGroupsTreeNode) superGroupsTree.getLastSelectedPathComponent();
					   			dialogFrameTextField.setText(superGroup.toString());
					   			for (int i =0;i<superGroup.getChildCount();i++){
					   				String currentGroupInTree = superGroup.getChildAt(i).toString();
					   				for(int j=0;j < groupsListModel.getSize();j++){
					   					String currentGroupInList = groupsListModel.get(j).toString();
					   					if(currentGroupInTree.equals(currentGroupInList)){
					   						selectedGroupsList.setSelectedIndex(j);	
					   					}
					   				}
					   			}
					   			selectPanel.add(selectedGroupsList);
					   		}
					   
					   	contentPane.add(defineResourcePanel,BorderLayout.CENTER);
					 
					   
					   	JPanel buttonPanel = new JPanel();
					   	SwingUtils.setFixedSize(buttonPanel,500,50);
					   	buttonPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE, Color.GRAY));
					   	
					   	dialogFrameConfirmButton = new JButton(Messages.getString("PetriNet.Resources.Ok.Title"));
					   	dialogFrameConfirmButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Ok"));
					   	dialogFrameConfirmButton.addActionListener(createSuperResource);
						SwingUtils.setFixedSize(dialogFrameConfirmButton,90,23);
					   	buttonPanel.add(dialogFrameConfirmButton);
					   	
					   	dialogFrameCancelButton = new JButton(Messages.getString
					   			("QuanlAna.ReachabilityGraph.Settings.Button.Cancel"));
					   	SwingUtils.setFixedSize(dialogFrameCancelButton,90,23);
					   	dialogFrameCancelButton.addActionListener(createSuperResource);
					   	buttonPanel.add(dialogFrameCancelButton);
					   	contentPane.add(buttonPanel,BorderLayout.SOUTH);
					   
					   dialogFrame.setContentPane(c);
					} catch (Exception exc) {
						exc.printStackTrace();
					}	   		
	   		 	}
		   
	   	   }
	   	   
	   	   private class editSuperResourceFrame implements ActionListener {
			   	
	   		 public void actionPerformed(ActionEvent e) {
				   try{
					   dialogFrame = new JFrame();
					  if(e.getSource()== superRolesEditButton){
						   dialogFrame.setTitle(Messages.getString
								   ("PetriNet.Resources.Resource.EditSuperRole"));
					  }
					  if(e.getSource()== superGroupsEditButton){
						   dialogFrame.setTitle(Messages.getString
								   ("PetriNet.Resources.Resource.EditSuperGroup"));
					  }
					   dialogFrame.setSize(500, 400);
					   dialogFrame.setLocationRelativeTo(rolesContentPanel);
					   dialogFrame.setVisible(true);
					   dialogFrame.setResizable(false);

					   dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		   		
					   java.awt.Container c =  dialogFrame.getContentPane();
					   JPanel contentPane = new JPanel(new BorderLayout());
					   contentPane.setBorder(BorderFactory.createEmptyBorder() );
					   contentPane.setBackground(Color.WHITE);
					   SwingUtils.setFixedSize(contentPane,500,400);
					   c.add(contentPane);
					   
					   JPanel namePanel = new JPanel(new GridBagLayout());
					   SwingUtils.setFixedSize(namePanel,500,50);
					   namePanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE, Color.GRAY));
					  
					   		GridBagConstraints a = new GridBagConstraints();
					   		a.weightx = 2;
					   		a.weighty = 1;
					   		JLabel nameLabel = new JLabel(Messages.getString
					   				("PetriNet.Resources.ResourceName")+":   ");
					   		dialogFrameTextField = new JTextField();
					   		SwingUtils.setFixedSize(dialogFrameTextField,300,25);
					
					   		a.gridx = 0;
					   		a.gridy = 0;
					   		a.anchor = GridBagConstraints.EAST;
					   		namePanel.add(nameLabel,a);
					 
					   		a.gridx = 1;					   
					   		a.gridy = 0;
					   		a.anchor = GridBagConstraints.WEST;
					   		namePanel.add(dialogFrameTextField,a);
					
					   		contentPane.add(namePanel,BorderLayout.NORTH);

					   JPanel defineResourcePanel = new JPanel(new GridBagLayout());
					   SwingUtils.setFixedSize(defineResourcePanel,500,300);
					   defineResourcePanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE, Color.GRAY));

					   		GridBagConstraints b = new GridBagConstraints();
					   		b.weightx = 1;
					   		b.weighty = 2;
					  
					   		JLabel addRolesLabel = new JLabel(("                         ")+
					   				(Messages.getString("PetriNet.Resources.Resource.AddtoSuperResource"))+(":"));
					   		b.gridx = 0;
					   		b.gridy = 1;
					   		b.anchor = GridBagConstraints.WEST;
					   		defineResourcePanel.add(addRolesLabel,b);
					   
					  
					   		JPanel selectPanel = new JPanel (new GridLayout());
					   		SwingUtils.setFixedSize(selectPanel,300,240);
					   		JScrollPane selectScrollPane = new JScrollPane();
					   		selectScrollPane.setBackground(Color.WHITE);
					   		
					   		SwingUtils.setFixedSize(selectScrollPane,300,260);
//					   		selectPanel.add(selectScrollPane);
					   		
					   		b.gridx = 0;
					   		b.gridy = 2;
					   		b.anchor = GridBagConstraints.CENTER;
					   		defineResourcePanel.add(selectPanel,b);

					   		if(e.getSource()==superRolesEditButton){
					   			selectedRolesList = new JList(rolesListModel);
					   			selectedRolesList.setSelectionModel(new ToggleSelectionModel());
					   			
					   			SuperRolesTreeNode superRole = (SuperRolesTreeNode) superRolesTree.getLastSelectedPathComponent();
					   			dialogFrameTextField.setText(superRole.toString());
					   			for (int i =0;i<superRole.getChildCount();i++){
					   				String currentRoleInTree = superRole.getChildAt(i).toString();
					   				for(int j=0;j < rolesListModel.getSize();j++){
					   					String currentRoleInList = rolesListModel.get(j).toString();
					   					if(currentRoleInTree.equals(currentRoleInList)){
					   						selectedRolesList.setSelectedIndex(j);	
					   					}
					   				}
					   			}
					   			selectPanel.add(selectedRolesList);
					   		}
					   		
					   		if(e.getSource()==superGroupsEditButton){
					   			selectedGroupsList = new JList (groupsListModel);
					   			
					   			selectedGroupsList.setSelectionModel(new ToggleSelectionModel());
					   			SuperGroupsTreeNode superGroup = (SuperGroupsTreeNode) superGroupsTree.getLastSelectedPathComponent();
					   			dialogFrameTextField.setText(superGroup.toString());
					   			for (int i =0;i<superGroup.getChildCount();i++){
					   				String currentGroupInTree = superGroup.getChildAt(i).toString();
					   				for(int j=0;j < groupsListModel.getSize();j++){
					   					String currentGroupInList = groupsListModel.get(j).toString();
					   					if(currentGroupInTree.equals(currentGroupInList)){
					   						selectedGroupsList.setSelectedIndex(j);	
					   					}
					   				}
					   			}
					   			selectPanel.add(selectedGroupsList);
					   		}
					   
					   	contentPane.add(defineResourcePanel,BorderLayout.CENTER);
					 
					   
					   	JPanel buttonPanel = new JPanel();
					   	SwingUtils.setFixedSize(buttonPanel,500,50);
					   	buttonPanel.setBorder(BorderFactory.createEtchedBorder(Color.WHITE, Color.GRAY));
					   	
					   	dialogFrameConfirmButton = new JButton(Messages.getString("PetriNet.Resources.Ok.Title"));
					   	dialogFrameConfirmButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Ok"));
					   	dialogFrameConfirmButton.addActionListener(editSuperResource);
						SwingUtils.setFixedSize(dialogFrameConfirmButton,90,23);
					   	buttonPanel.add(dialogFrameConfirmButton);
					   	
					   	dialogFrameCancelButton = new JButton(Messages.getString
					   			("QuanlAna.ReachabilityGraph.Settings.Button.Cancel"));
					   	SwingUtils.setFixedSize(dialogFrameCancelButton,90,23);
					   	dialogFrameCancelButton.addActionListener(editSuperResource);
					   	buttonPanel.add(dialogFrameCancelButton);
					   	contentPane.add(buttonPanel,BorderLayout.SOUTH);
					   
					   dialogFrame.setContentPane(c);
					} catch (Exception exc) {
						exc.printStackTrace();
					}	   		
	   		 }
		   
	   	   }
	   	   
	   	   
	       class ToggleSelectionModel extends DefaultListSelectionModel
	       {
	          
	           boolean gestureStarted = false;
	          
	           public void setSelectionInterval(int index0, int index1) {
	               if(getSelectionMode()==SINGLE_SELECTION)
	               {
	                   super.setSelectionInterval(index0, index1);
	               } else
	               {
	                   if (isSelectedIndex(index0) && !gestureStarted) {
	                       super.removeSelectionInterval(index0, index1);
	                   }
	                   else {
	                       super.addSelectionInterval(index0, index1);
	                   }
	                   gestureStarted = true;
	               }
	           }

	           public void setValueIsAdjusting(boolean isAdjusting) {
	               if (isAdjusting == false) {
	                   gestureStarted = false;
	               }
	           }
	       }
	       
	       
	       
	   private class createSuperResource implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==dialogFrameCancelButton){
				dialogFrame.dispose();
			}else{
				if(selectedGroupsList==null){				
				try{
					Object [] selectedRoles = selectedRolesList.getSelectedValues();

					if(selectedRoles.length<2){
						JOptionPane.showMessageDialog(dialogFrame , Messages.getString
								("ResourceEditor.Error.NoRolesChoosen.Text"), 
								Messages.getString("ResourceEditor.Error.NoRolesChoosen.Title"),
		                        JOptionPane.ERROR_MESSAGE);	
						
					}else{
						if(checkClassSyntax(dialogFrameTextField.getText())){
							String superRole = dialogFrameTextField.getText();
							ResourceClassModel newSuperRole = new ResourceClassModel(superRole, ResourceClassModel.TYPE_ROLE);
							getPetrinet().addRole(newSuperRole);
							rolesListModel.addElement(newSuperRole);
							SuperRolesTreeNode superRoleNode = new SuperRolesTreeNode(superRole);
							rolesTreeModel.insertNodeInto(superRoleNode, superRolesTopNode,  superRolesTopNode.getChildCount());
						
							for(int i=0;i<selectedRoles.length;i++){
								String roleName = selectedRoles[i].toString();
								int j = getPetrinet().containsRole(roleName);
								ResourceClassModel currentRole = getPetrinet().getRoles().get(j);
								currentRole.addSuperModel(newSuperRole);

							
								DefaultMutableTreeNode child = new DefaultMutableTreeNode(roleName);
								superRolesTreeModel.insertNodeInto(child, superRoleNode, superRoleNode.getChildCount());
								ArrayList <String> objects = getObjectsAssignedToResource(currentRole, ResourceClassModel.TYPE_ROLE);
								for(int a = 0; a< objects.size();a++ ){
									String currentObject = objects.get(a);
									System.out.println(currentObject);
									petrinet.addResourceMapping(newSuperRole.toString(), currentObject);
								}
								
							}
							refreshFromModel();
							
							getEditor().setSaved(false);
						}
					}dialogFrame.dispose();
				}catch(Exception exc){
					exc.printStackTrace();
				}
				}
				if(selectedRolesList==null&&selectedGroupsList!=null){
					Object [] selectedGroups = selectedGroupsList.getSelectedValues();
					if(selectedGroups.length<2){
						JOptionPane.showMessageDialog(dialogFrame , Messages.getString("ResourceEditor.Error.NoGroupsChoosen.Text"), 
								Messages.getString("ResourceEditor.Error.NoGroupsChoosen.Title"),
		                        JOptionPane.INFORMATION_MESSAGE);				
					}else{
						if(checkClassSyntax(dialogFrameTextField.getText())){
							String superGroup = dialogFrameTextField.getText();
							ResourceClassModel newSuperGroup = new ResourceClassModel(superGroup, ResourceClassModel.TYPE_ORGUNIT);
							getPetrinet().addOrgUnit(newSuperGroup);
							groupsListModel.addElement(newSuperGroup);
							SuperGroupsTreeNode superGroupNode = new SuperGroupsTreeNode(superGroup);
							groupsTreeModel.insertNodeInto(superGroupNode, superGroupsTopNode,  superGroupsTopNode.getChildCount());
						
							for(int i=0;i<selectedGroups.length;i++){
								String groupName = selectedGroups[i].toString();
								int j = getPetrinet().containsOrgunit(groupName);
								ResourceClassModel currentGroup = getPetrinet().getOrganizationUnits().get(j);
								currentGroup.addSuperModel(newSuperGroup);
						
								DefaultMutableTreeNode child = new DefaultMutableTreeNode(groupName);
								superRolesTreeModel.insertNodeInto(child, superGroupNode, superGroupNode.getChildCount());
								ArrayList <String> objects = getObjectsAssignedToResource(currentGroup, ResourceClassModel.TYPE_ORGUNIT);
								for(int a = 0; a< objects.size();a++ ){
									String currentObject = objects.get(a);
									System.out.println(currentObject);
									petrinet.addResourceMapping(newSuperGroup.toString(), currentObject);
								}
					
							}
							refreshFromModel();
							dialogFrame.dispose();
							getEditor().setSaved(false);
							
							
						}
					}
				}
			}	
			dialogFrame.dispose();
			selectedGroupsList = null;
			selectedRolesList = null;
			
			}
		   
		}
	   
	   
	   private class editSuperResource implements ActionListener{

			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==dialogFrameCancelButton){
					dialogFrame.dispose();
				}else{
					if(selectedGroupsList==null){				
					Object [] selectedRoles = selectedRolesList.getSelectedValues();
						if(selectedRoles.length<2){
							JOptionPane.showMessageDialog(dialogFrame , Messages.getString("ResourceEditor.Error.NoRolesChoosen.Text"),
									Messages.getString("ResourceEditor.Error.NoRolesChoosen.Title"),
			                        JOptionPane.ERROR_MESSAGE);				
						}else{
							boolean checkedName;
							if(dialogFrameTextField.getText().equals(superRolesTree.getLastSelectedPathComponent().toString()))
							{
								checkedName=true;
							}else {
								checkedName=checkClassSyntax(dialogFrameTextField.getText());
							}
							if(checkedName){
								String superRoleNewName = dialogFrameTextField.getText();
								ResourceClassModel newSuperRole = new ResourceClassModel(superRoleNewName, ResourceClassModel.TYPE_ROLE);
								String superRoleOldName = superRolesTree.getLastSelectedPathComponent().toString();
								SuperRolesTreeNode superRole = (SuperRolesTreeNode) superRolesTree.getLastSelectedPathComponent();
								 for(int i=0;i<superRole.getChildCount();i++){
									 String role = superRole.getChildAt(i).toString();
									 int j = getPetrinet().containsRole(role);
									 ResourceClassModel roleModel = (ResourceClassModel) getPetrinet().getRoles().get(j);
									 ResourceClassModel superRoleModel= new ResourceClassModel(superRoleOldName,
											 ResourceClassModel.TYPE_ROLE);
									 roleModel.removeSuperModel(superRoleModel);
								  }
								
								int j = getPetrinet().containsRole(superRoleOldName);
								ResourceClassModel roleModel = (ResourceClassModel) getPetrinet().getRoles().get(j);
				                roleModel.setName(superRoleNewName);
				                
				                updateRolesInPetrinet(superRoleOldName, superRoleNewName);
								
							
								for(int i=0;i<selectedRoles.length;i++){
									String roleName = selectedRoles[i].toString();
									int a = getPetrinet().containsRole(roleName);
									ResourceClassModel currentRole = getPetrinet().getRoles().get(a);
									currentRole.addSuperModel(newSuperRole);
									ArrayList <String> objects = getObjectsAssignedToResource(currentRole, ResourceClassModel.TYPE_ROLE);
									for(int b = 0; b< objects.size();b++ ){
										String currentObject = objects.get(b);
										System.out.println(currentObject);
										petrinet.addResourceMapping(newSuperRole.toString(), currentObject);
									}
								}
								refreshFromModel();
								
								getEditor().setSaved(false);
							
								superRolesEditButton.setEnabled(false);
								superRolesDeleteButton.setEnabled(false);
							}
						}
					}
					if(selectedRolesList==null){
						Object [] selectedGroups = selectedGroupsList.getSelectedValues();
						if(selectedGroups.length<2){
							JOptionPane.showMessageDialog(dialogFrame , 
									Messages.getString("ResourceEditor.Error.NoGroupsChoosen.Text"), Messages.getString("ResourceEditor.Error.NoGroupsChoosen.Title"),
			                        JOptionPane.ERROR_MESSAGE);				
						}else{
							boolean checkedName;
							if(dialogFrameTextField.getText().equals(superGroupsTree.getLastSelectedPathComponent().toString()))
							{
								checkedName=true;
							}else {
								checkedName=checkClassSyntax(dialogFrameTextField.getText());
							}
							if(checkedName){
								String superGroupNewName = dialogFrameTextField.getText();
								ResourceClassModel newSuperGroup = new ResourceClassModel(superGroupNewName, 
										ResourceClassModel.TYPE_ORGUNIT);
								String superGroupOldName = superGroupsTree.getLastSelectedPathComponent().toString();
								SuperGroupsTreeNode superGroup = (SuperGroupsTreeNode) superGroupsTree.getLastSelectedPathComponent();
								 for(int i=0;i<superGroup.getChildCount();i++){
									 String group = superGroup.getChildAt(i).toString();
									 int j = getPetrinet().containsOrgunit(group);
									 ResourceClassModel groupModel = (ResourceClassModel) getPetrinet().getOrganizationUnits().get(j);
									 ResourceClassModel superGroupModel= new ResourceClassModel(superGroupOldName,
											 ResourceClassModel.TYPE_ORGUNIT);
									 groupModel.removeSuperModel(superGroupModel);
								  }
								
								int j = getPetrinet().containsOrgunit(superGroupOldName);
								ResourceClassModel groupModel = (ResourceClassModel) getPetrinet().getOrganizationUnits().get(j);
				                groupModel.setName(superGroupNewName);
				                
				                updateRolesInPetrinet(superGroupOldName, superGroupNewName);	
							
				                for(int i=0;i<selectedGroups.length;i++){
									String groupName = selectedGroups[i].toString();
									int a = getPetrinet().containsOrgunit(groupName);
									ResourceClassModel currentGroup = getPetrinet().getOrganizationUnits().get(a);
									currentGroup.addSuperModel(newSuperGroup);
									ArrayList <String> objects = getObjectsAssignedToResource(currentGroup, 
											ResourceClassModel.TYPE_ORGUNIT);
									for(int b = 0; b< objects.size();b++ ){
										String currentObject = objects.get(b);
										System.out.println(currentObject);
										petrinet.addResourceMapping(newSuperGroup.toString(), currentObject);
									}
								}	
								refreshFromModel();
								
								getEditor().setSaved(false);
					
								superGroupsEditButton.setEnabled(false);
								superGroupsDeleteButton.setEnabled(false);
							}
						}
					}
				}	
				selectedGroupsList = null;
				selectedRolesList = null;
				
				}
			   
			}

	   
	   private class removeSuperResource implements ActionListener{

			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==superRolesDeleteButton){
					try{
						  String superrole2remove = (superRolesTree.getLastSelectedPathComponent().toString());
						  if(!roleIsUsed(superrole2remove)){
							  SuperRolesTreeNode superRole= (SuperRolesTreeNode) superRolesTree.getLastSelectedPathComponent();
							  for(int i=0;i<superRole.getChildCount();i++){
								 String role = superRole.getChildAt(i).toString();
								 int j = getPetrinet().containsRole(role);
								 ResourceClassModel roleModel = (ResourceClassModel) getPetrinet().getRoles().get(j);
								 ResourceClassModel superRoleModel= 
									 new ResourceClassModel(superrole2remove,ResourceClassModel.TYPE_ROLE);
								 roleModel.removeSuperModel(superRoleModel);
							  }
						  
						  	 int a = getPetrinet().containsRole(superrole2remove);
							 getPetrinet().getRoles().remove(a);
							 
							 refreshFromModel();
							 
							 getEditor().setSaved(false);
				
							 superRolesDeleteButton.setEnabled(false);
							 superRolesEditButton.setEnabled(false);
						  }else{
						         JOptionPane.showMessageDialog(rolesContentPanel, 
						        		 Messages.getString("ResourceEditor.Error.UsedResourceClass.Text"), 
						        		 Messages.getString("ResourceEditor.Error.UsedResourceClass.Title"),
						                        JOptionPane.ERROR_MESSAGE);
						       }
					}catch (Exception exc){
						exc.printStackTrace();
					}
				}
				if(e.getSource()==superGroupsDeleteButton){
					try{
						  String supergroup2remove = (superGroupsTree.getLastSelectedPathComponent().toString());
						  if(!groupIsUsed(supergroup2remove)){
							  SuperGroupsTreeNode superGroup= (SuperGroupsTreeNode) superGroupsTree.getLastSelectedPathComponent();
							  for(int i=0;i<superGroup.getChildCount();i++){
								 String group = superGroup.getChildAt(i).toString();
								 int j = getPetrinet().containsOrgunit(group);
								 ResourceClassModel groupModel = (ResourceClassModel) getPetrinet().getOrganizationUnits().get(j);
								 ResourceClassModel superGroupModel= new ResourceClassModel(supergroup2remove,ResourceClassModel.TYPE_ORGUNIT);
								 groupModel.removeSuperModel(superGroupModel);
							  }
						  
						  	 int a = getPetrinet().containsOrgunit(supergroup2remove);
							 getPetrinet().getOrganizationUnits().remove(a);
							 
							 refreshFromModel();
							 
							 getEditor().setSaved(false);
							 
							 superGroupsDeleteButton.setEnabled(false);
							 superGroupsEditButton.setEnabled(false);
					  }else{
					         JOptionPane.showMessageDialog(rolesContentPanel, 
					        		 Messages.getString("ResourceEditor.Error.UsedResourceClass.Text"), 
					        		 Messages.getString("ResourceEditor.Error.UsedResourceClass.Title"),
					                        JOptionPane.ERROR_MESSAGE);
					       }
					}catch (Exception exc){
						exc.printStackTrace();
					}
				}
			}
		}

		   

//*****************************TREE-NODE**********************
	   //****************************************************
	   class ObjectsTreeNode extends DefaultMutableTreeNode {
		   	public ObjectsTreeNode(){super();}
			public ObjectsTreeNode(String name){super(name);}
	   }
	   
	   class RolesTreeNode extends DefaultMutableTreeNode {
		   public RolesTreeNode(){
			   super();
			   }
		   public RolesTreeNode(String name){super(name);}
	   }
	   class SuperRolesTreeNode extends DefaultMutableTreeNode {
		   public SuperRolesTreeNode(){super();}
		   public SuperRolesTreeNode(String name){super(name);}
	   }
	   
	   class GroupsTreeNode extends DefaultMutableTreeNode {
		   public GroupsTreeNode(){super();}
		   public GroupsTreeNode(String name){super(name);}
	   }
	   class SuperGroupsTreeNode extends DefaultMutableTreeNode {
		   public SuperGroupsTreeNode(){super();}
		   public SuperGroupsTreeNode(String name){super(name);}
	   }
	   
	   
	   
	   private class createResource implements ActionListener{

		   public void actionPerformed(ActionEvent e) {
			   try{
				  
				  

					   if(e.getSource()== rolesNewButton ){
						   JOptionPane nameDialog = new JOptionPane ();
						   String newResourceName = JOptionPane.showInputDialog(nameDialog, 
								   (Object)Messages.getString("PetriNet.Resources.ResourceName"),
								   Messages.getString("PetriNet.Resources.CreateRole"),JOptionPane.QUESTION_MESSAGE);
						   if (checkClassSyntax(newResourceName )){
							   ResourceClassModel newRole = new ResourceClassModel(newResourceName, ResourceClassModel.TYPE_ROLE);
							   getPetrinet().addRole(newRole);
							   rolesListModel.addElement(newRole);
							   refreshRolesTreeFromListModel();
							   refreshGroupsTreeFromListModel();
							   refreshObjectsFromModel();
							   getEditor().setSaved(false);
						   }
					   }
					   if(e.getSource()== groupsNewButton ){
						   JOptionPane nameDialog = new JOptionPane ();
						   String newResourceName = JOptionPane.showInputDialog(nameDialog, 
								   (Object)Messages.getString("PetriNet.Resources.ResourceName"),
								   Messages.getString("PetriNet.Resources.CreateGroup"),JOptionPane.QUESTION_MESSAGE);
						   if (checkClassSyntax(newResourceName )){
							   ResourceClassModel newGroup = new ResourceClassModel(newResourceName, 
									   ResourceClassModel.TYPE_ORGUNIT);
							   getPetrinet().addOrgUnit(newGroup);
							   groupsListModel.addElement(newGroup);
							   refreshRolesTreeFromListModel();
							   refreshGroupsTreeFromListModel();
							   refreshObjectsFromModel();
							   getEditor().setSaved(false);
						   }
					   }
					   if(e.getSource()== objectsNewButton ){
						   JOptionPane nameDialog = new JOptionPane ();
						   String newResourceName = JOptionPane.showInputDialog(nameDialog,
								   (Object)Messages.getString("PetriNet.Resources.ResourceName"),
								   Messages.getString("PetriNet.Resources.CreateObject"),JOptionPane.QUESTION_MESSAGE);
						   if (checkClassSyntax(newResourceName )){
							   ResourceModel newObject = new ResourceModel(newResourceName);
							   	getPetrinet().addResource(newObject);
							   	objectsUnassignedListModel.addElement(newObject);
							   	refreshObjectsTreeFromListModel();
							   	getEditor().setSaved(false);}
					   }
				   
				   
			   }catch (Exception ex){}
		   }  
	   }
	   

	   public void addGroupsNode(DefaultTreeModel model, DefaultMutableTreeNode parent, String name) {
		    GroupsTreeNode childNode = new GroupsTreeNode(name);
		    model.insertNodeInto(childNode, parent, parent.getChildCount());
	   }
	   public void addRolesNode(DefaultTreeModel model, DefaultMutableTreeNode parent, String name) {
		    RolesTreeNode childNode = new RolesTreeNode(name);
		    model.insertNodeInto(childNode, parent, parent.getChildCount());
	   }
	   public void addObjectsNode(DefaultTreeModel model, DefaultMutableTreeNode parent, String name) {
		    ObjectsTreeNode childNode = new ObjectsTreeNode(name);
		    model.insertNodeInto(childNode, parent, parent.getChildCount());
		    objectsDeleteButton.setEnabled(true);
		    objectsEditButton.setEnabled(true);
	   }
	   
// ******************** REMOVE-RESSOURCE
	   
	   private class removeResource implements ActionListener{
	
		   @SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e) {
			 
			   try{
//				   Remove an Object
				   if(e.getSource()== objectsDeleteButton){
					   if(!objectsTree.getLastSelectedPathComponent().equals(objectsUnassignedNode)&&
							   !objectsTree.getLastSelectedPathComponent().equals(objectsTopNode)&&
							   !objectsTree.getLastSelectedPathComponent().equals(objectsAssignedNode)){
						   MutableTreeNode nodeToDelete = (MutableTreeNode) objectsTree.getLastSelectedPathComponent();
						  
						   if(nodeToDelete.getParent().equals(objectsUnassignedNode)){
							   objectsTreeModel.removeNodeFromParent(nodeToDelete);  
							   objectsUnassignedListModel.removeElement(nodeToDelete.toString());
							   int j = getPetrinet().containsResource(nodeToDelete.toString());
							   objectsUnassignedListModel.removeElement(j);
				               getPetrinet().getResources().remove(j);
							 	objectsEditButton.setEnabled(false);
							 	objectsDeleteButton.setEnabled(false);
				               getEditor().setSaved(false);
							  
						   }
						   else{
							
							   for (int i=0;i < rolesTopNode.getChildCount();i++){
								  
								   RolesTreeNode parent = (RolesTreeNode) rolesTopNode.getChildAt(i);
								   for (int j=0;j<parent.getChildCount();j++){
									   MutableTreeNode  child = (MutableTreeNode) parent.getChildAt(j);
									   if(child.toString().equals(nodeToDelete.toString())){
										   child.removeFromParent();
										   rolesTree.updateUI();		
									   }
								   }
							   }
							   for (int i=0;i < groupsTopNode.getChildCount();i++){
									  
								   GroupsTreeNode parent = (GroupsTreeNode) groupsTopNode.getChildAt(i);
								   for (int j=0;j<parent.getChildCount();j++){
									   MutableTreeNode  child = (MutableTreeNode) parent.getChildAt(j);
									   if(child.toString().equals(nodeToDelete.toString())){
										   child.removeFromParent();
										   groupsTree.updateUI();
			
									   }
								   }
							   } 
							   objectsTreeModel.removeNodeFromParent(nodeToDelete);
							   objectsUnassignedListModel.removeElement(nodeToDelete.toString());
							   int j = getPetrinet().containsResource(nodeToDelete.toString());
							   objectsUnassignedListModel.removeElement(j);
				               getPetrinet().getResources().remove(j);
				               refreshFromModel();
							 	objectsEditButton.setEnabled(false);
							 	objectsDeleteButton.setEnabled(false);
				               getEditor().setSaved(false);
						   }
					if(objectsAssignedNode.getChildCount()==0&&objectsUnassignedNode.getChildCount()==0){
							objectsDeleteButton.setEnabled(false);
							objectsEditButton.setEnabled(false); 
						   }
						   objectsTree.updateUI();
					   }
				   	}
			   }
			   catch (NullPointerException npe){
				   npe.printStackTrace();
			   }
//			   Remove a Role
			   if(e.getSource()== rolesDeleteButton){
				  try{
					  String role2remove = rolesTree.getLastSelectedPathComponent().toString();
					  if(!roleIsUsed(role2remove)){
						if(rolesDeleteButton.getToolTipText().equals(Messages.getString("PetriNet.Resources.Delete.Title"))){
						// Delete a role
							RolesTreeNode nodeToDelete = (RolesTreeNode) rolesTree.getLastSelectedPathComponent();
							int j = getPetrinet().containsRole(role2remove);	
							
							if(getPetrinet().getRoles().get(j).getSuperModels()!= null){
								  JOptionPane.showMessageDialog(rolesContentPanel, 
										  Messages.getString("ResourceEditor.Error.UsedResourceInSuperRole.Text"), 
										  Messages.getString("ResourceEditor.Error.UsedResourceClass.Title"),
					                        JOptionPane.ERROR_MESSAGE);
							}else{
								if(nodeToDelete.getChildCount()==0){
					                getPetrinet().getRoles().remove(j);
					                refreshFromModel();
					 
					                getEditor().setSaved(false);
								}else{
									getPetrinet().getRoles().remove(j);	
									for(int i=0;i<nodeToDelete.getChildCount();i++){
										String object2unassign = nodeToDelete.getChildAt(i).toString();	
										getPetrinet().removeResourceMapping(nodeToDelete.toString(), object2unassign);
									} 
					                refreshFromModel();
					     
									getEditor().setSaved(false);
							  	
								}
							}
						}
						
						// Unassign an object
						else{
							String object2unassign = rolesTree.getLastSelectedPathComponent().toString();
							
							DefaultMutableTreeNode child =  (DefaultMutableTreeNode) rolesTree.getLastSelectedPathComponent();
							RolesTreeNode parent = (RolesTreeNode) child.getParent();
							
							int path = rolesTopNode.getIndex(parent);

							Vector assignedClasses = getPetrinet().getResourceClassesResourceIsAssignedTo(object2unassign.toString());
							Object ass;
							for (Iterator iter = assignedClasses.iterator(); iter.hasNext();){
			    				ass = iter.next();
			    				if(ass.toString().equals(parent.toString())){
			    					 getPetrinet().removeResourceMapping(parent.toString(), object2unassign);
			    				}
							}	
			                refreshFromModel();
						  	
						  	rolesDeleteButton.setEnabled(false);
						  	
						  	rolesTree.expandRow(path);
						}
						  
						  if(rolesTopNode.getChildCount()==0){
							rolesDeleteButton.setEnabled(false);
							rolesEditButton.setEnabled(false);
						  }			
						getEditor().setSaved(false);
					  }
			       else{
			         JOptionPane.showMessageDialog(rolesContentPanel, 
			        		 Messages.getString("ResourceEditor.Error.UsedResourceClass.Text"), 
			        		 Messages.getString("ResourceEditor.Error.UsedResourceClass.Title"),
			                        JOptionPane.ERROR_MESSAGE);
			       }
				  }
				  catch(Exception exc){
					  exc.printStackTrace();
				  }
			   }
			   
			   if(e.getSource()== groupsDeleteButton){
				  try{
					  String group2remove = groupsTree.getLastSelectedPathComponent().toString();
					  if(!groupIsUsed(group2remove)){




						  if(groupsDeleteButton.getToolTipText().equalsIgnoreCase(
								  Messages.getString("PetriNet.Resources.Delete.Title"))){

				  //Delete a group
							  GroupsTreeNode nodeToDelete = (GroupsTreeNode) groupsTree.getLastSelectedPathComponent();
							  int j = getPetrinet().containsOrgunit(group2remove);
//							  Iterator i = getPetrinet().getOrganizationUnits().get(j).getSuperModels();
								if(getPetrinet().getOrganizationUnits().get(j).getSuperModels()!=null){
									  JOptionPane.showMessageDialog(rolesContentPanel,
											  Messages.getString("ResourceEditor.Error.UsedResourceInSuperGroup.Text"),
											  Messages.getString("ResourceEditor.Error.UsedResourceClass.Title"),
						                        JOptionPane.ERROR_MESSAGE);
								}else{
									if(nodeToDelete.getChildCount()==0){
						                getPetrinet().getOrganizationUnits().remove(j);
						                refreshFromModel();
						    
										
										getEditor().setSaved(false);
									}else{
								  		getPetrinet().getOrganizationUnits().remove(j);	
								  		for(int i=0;i<nodeToDelete.getChildCount();i++){
											String object2unassign = nodeToDelete.getChildAt(i).toString();	
											getPetrinet().removeResourceMapping(nodeToDelete.toString(), object2unassign);
								  		}
						                refreshFromModel();
						       
								  		
								  		getEditor().setSaved(false);
									}
								}
						}	  
					  // Unassign an object
					  else{
							String object2unassign = groupsTree.getLastSelectedPathComponent().toString();
							
							DefaultMutableTreeNode child = (DefaultMutableTreeNode) groupsTree.getLastSelectedPathComponent();
							GroupsTreeNode parent = (GroupsTreeNode) child.getParent();
							
							int path = groupsTopNode.getIndex(parent);
							
							Vector assignedClasses = getPetrinet().getResourceClassesResourceIsAssignedTo(object2unassign.toString());
							Object ass;
							for (Iterator iter = assignedClasses.iterator(); iter.hasNext();){
			    				ass = iter.next();
			    				if(ass.toString().equals(parent.toString())){
			    					 getPetrinet().removeResourceMapping(parent.toString(), object2unassign);
			    				}
						  		
							}
			                refreshFromModel();
			      
							groupsTree.expandRow(path);
							groupsDeleteButton.setEnabled(false);
					  	}
						  
						  if (groupsTopNode.getChildCount()==0){
							groupsDeleteButton.setEnabled(false);
							groupsEditButton.setEnabled(false);
						  }
;
					  }
					  else{
						  JOptionPane.showMessageDialog(rolesContentPanel, 
								  Messages.getString("ResourceEditor.Error.UsedResourceClass.Text"), 
								  Messages.getString("ResourceEditor.Error.UsedResourceClass.Title"),
			                        JOptionPane.ERROR_MESSAGE);
					  }
					  getEditor().setSaved(false);
				  	}catch(NullPointerException npe){
					  npe.printStackTrace();
				  }
				  
			   }
		   }
	   }
	   

	   
	   private class editResource implements ActionListener{
			
		   public void actionPerformed(ActionEvent e) {
			try{   
			   if(e.getSource()== rolesEditButton){
				   Object message = (Object) rolesTree.getLastSelectedPathComponent().toString();
				   String oldName = (String) message;
				   JOptionPane nameDialog = new JOptionPane(); 
				   String newName = (String) JOptionPane.showInputDialog(nameDialog,
						   Messages.getString("PetriNet.Resources.ResourceName"),
						   Messages.getString("PetriNet.Resources.EditRole"),
                           JOptionPane.QUESTION_MESSAGE,null,null,message);
				   if (checkClassSyntax(newName )){
					   int j = getPetrinet().containsRole(oldName);
					   ResourceClassModel roleModel = (ResourceClassModel) getPetrinet().getRoles().get(j);
	                   roleModel.setName(newName);
	                   rolesListModel.set( j, roleModel);
	                   updateRolesInPetrinet(oldName, newName);
	                   refreshFromModel();
	        
	                   getEditor().setSaved(false);
				   }
			   }
			   
			   if(e.getSource()== groupsEditButton){
				   Object message = (Object) groupsTree.getLastSelectedPathComponent().toString();
				   String oldName = (String) message;
				   JOptionPane nameDialog = new JOptionPane(); 
				   String newName = (String) JOptionPane.showInputDialog(nameDialog,
						   Messages.getString("PetriNet.Resources.ResourceName"),
						   Messages.getString("PetriNet.Resources.EditGroup"),
                           JOptionPane.QUESTION_MESSAGE,null,null,message);
				   if (checkClassSyntax(newName )){
					   int j = getPetrinet().containsOrgunit(oldName);
					   ResourceClassModel groupModel = (ResourceClassModel) getPetrinet().getOrganizationUnits().get(j);
	                   groupModel.setName(newName);
	                   groupsListModel.set( j, groupModel);
	                   updateGroupsInPetrinet(oldName, newName);
	                 
	                   refreshFromModel();
	                
	                   getEditor().setSaved(false);
				   }
			   }
			   if(e.getSource()==objectsEditButton){
				   Object message = (Object) objectsTree.getLastSelectedPathComponent().toString();
				   JOptionPane nameDialog = new JOptionPane(); 
				   String newName = (String) JOptionPane.showInputDialog(nameDialog,
						   Messages.getString("PetriNet.Resources.ResourceName"),
						   Messages.getString("PetriNet.Resources.EditObject"),
                           JOptionPane.QUESTION_MESSAGE,null,null,message);
				 if (checkClassSyntax(newName )){
					 	getPetrinet().replaceResourceMapping(message.toString(), newName);
		                ResourceModel resourceModel = (ResourceModel) getPetrinet().getResources().get(getPetrinet().containsResource(message.toString()));
		                resourceModel.setName(newName);
		         
		                refreshFromModel();
				 }
			   }
			   getEditor().setSaved(false);
			}catch(Exception exc){
				exc.printStackTrace();
			}
		   }
		}

	
	   
	   

	   class MyTreeSelectionListener implements TreeSelectionListener{
		   public void valueChanged(TreeSelectionEvent e) {	
			   try{
			   if (e.getSource()==rolesTree){
				   DefaultMutableTreeNode node = ( DefaultMutableTreeNode)rolesTree.getLastSelectedPathComponent();
				 
				   if(node.isLeaf()&& !node.getParent().toString().equals(rolesTopNode.toString())){
					   rolesDeleteButton.setToolTipText(Messages.getString("PetriNet.Resources.Unassign.Title")); 
					   rolesDeleteButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Unassign"));
					   rolesEditButton.setEnabled(false);
					   rolesDeleteButton.setEnabled(true);
					   
				   }else{
					  rolesDeleteButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Delete"));
					  rolesDeleteButton.setToolTipText(Messages.getString("PetriNet.Resources.Delete.Title"));
					  rolesEditButton.setEnabled(true);
					  rolesDeleteButton.setEnabled(true);
				   }
			   }
			   if (e.getSource()==groupsTree){
				   MutableTreeNode node = ( MutableTreeNode)groupsTree.getLastSelectedPathComponent();
				   if(node.isLeaf()&& !node.getParent().toString().equals(groupsTopNode.toString())){
					   groupsDeleteButton.setToolTipText(Messages.getString("PetriNet.Resources.Unassign.Title")); 
					   groupsDeleteButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Unassign"));
					   groupsEditButton.setEnabled(false);
					   groupsDeleteButton.setEnabled(true);
				   }else{
			        	groupsDeleteButton.setIcon(Messages.getImageIcon("PetriNet.Resources.Delete"));
			        	groupsDeleteButton.setToolTipText(Messages.getString("PetriNet.Resources.Delete.Title"));
			        	groupsDeleteButton.setEnabled(true);
			        	groupsEditButton.setEnabled(true);
				   }
				 } 
			   if(e.getSource()==superRolesTree){
				   MutableTreeNode node = ( MutableTreeNode)superRolesTree.getLastSelectedPathComponent();
				   if(node.getParent().toString().equals(superRolesTopNode.toString())){
					   superRolesDeleteButton.setEnabled(true);
					   superRolesEditButton.setEnabled(true);
				   }else{
					   superRolesDeleteButton.setEnabled(false);
					   superRolesEditButton.setEnabled(false);
				   }
			   }
			   if(e.getSource()==superGroupsTree){
				   MutableTreeNode node = ( MutableTreeNode)superGroupsTree.getLastSelectedPathComponent();
				   if(node.getParent().toString().equals(superGroupsTopNode.toString())){
					   superGroupsDeleteButton.setEnabled(true);
					   superGroupsEditButton.setEnabled(true);
				   }else{
					   superGroupsDeleteButton.setEnabled(false);
					   superGroupsEditButton.setEnabled(false);
				   }
			   }
			  
			   if (e.getSource()== objectsTree){
					 MutableTreeNode node = ( MutableTreeNode)objectsTree.getLastSelectedPathComponent();
					 if(node.isLeaf()&& !node.getParent().toString().equals(objectsTopNode.toString())){
						   objectsEditButton.setEnabled(true);
						   objectsDeleteButton.setEnabled(true);
					 }else{
						 	objectsEditButton.setEnabled(false);
						 	objectsDeleteButton.setEnabled(false);
					 }
			   }
		   }catch (Exception exc){
			   exc.printStackTrace();
		   }
		}		   
	   }
	   
	   private class MyTreeExpansionListener implements TreeExpansionListener{

		public void treeCollapsed(TreeExpansionEvent e) {
			if(e.getSource()==rolesTree){
				if(rolesTree.getRowCount()==rolesTopNode.getChildCount()){
					rolesCollapseButton.setEnabled(false);
				}
			}
			if(e.getSource()==superRolesTree){
				if(superRolesTree.getRowCount()==superRolesTopNode.getChildCount()){
					superRolesCollapseButton.setEnabled(false);
				}
			}
			if(e.getSource()==groupsTree){
				if(groupsTree.getRowCount()==groupsTopNode.getChildCount()){
					groupsCollapseButton.setEnabled(false);
				}
			}
			if(e.getSource()==superGroupsTree){
				if(superGroupsTree.getRowCount()==superGroupsTopNode.getChildCount()){
					superGroupsCollapseButton.setEnabled(false);
				}
			}
			if(e.getSource()==objectsTree){
				if(objectsTree.getRowCount()==objectsTopNode.getChildCount()){
					objectsCollapseButton.setEnabled(false);
				}
			}
			
		}

		public void treeExpanded(TreeExpansionEvent e) {
			if(e.getSource()==rolesTree){
				if(rolesTree.getRowCount()>rolesTopNode.getChildCount()){
					rolesCollapseButton.setEnabled(true);
				}
			}
			if(e.getSource()==superRolesTree){
				if(superRolesTree.getRowCount()>superRolesTopNode.getChildCount()){
					superRolesCollapseButton.setEnabled(true);
				}
			}
			if(e.getSource()==groupsTree){
				if(groupsTree.getRowCount()>groupsTopNode.getChildCount()){
					groupsCollapseButton.setEnabled(true);
				}
			}
			if(e.getSource()==superGroupsTree){
				if(superGroupsTree.getRowCount()>superGroupsTopNode.getChildCount()){
					superGroupsCollapseButton.setEnabled(true);
				}
			}
			if(e.getSource()==objectsTree){
				if(objectsTree.getRowCount()>objectsTopNode.getChildCount()){
					objectsCollapseButton.setEnabled(true);
				}
			}
			
		}
		   
	   }
   
	   
	   //********************Expand_Action_Listener **********************************
	   private class expandButtonListener implements ActionListener{
			
		   public void actionPerformed(ActionEvent e) {
			   
			   if(e.getSource()== objectsExpandButton){
				   expandAll(objectsTree);
			   }
			   if(e.getSource()== rolesExpandButton){
				   expandAll(rolesTree);
			   }
			   if(e.getSource()== superRolesExpandButton){
				   expandAll(superRolesTree);
			   }
			   if(e.getSource()== groupsExpandButton){
				   expandAll(groupsTree);
			   }
			   if(e.getSource()== superGroupsExpandButton){
				   expandAll(superGroupsTree);
			   }
			 
			   
			   
		   }
	   }
	   
	   
	   //***************************EXPAND -ALL****************************************
	   public void expandAll(JTree tree) {
		    int row = 0;
		    while (row < tree.getRowCount()) {
		      tree.expandRow(row);
		      row++;
		      }
		    }
	   //********************Collapse_Acion_Listener **********************************
	   private class collapseButtonListener implements ActionListener{
			
		   public void actionPerformed(ActionEvent e) {
			   
			   if(e.getSource()== objectsCollapseButton){
				   collapseAll(objectsTree);
				   objectsCollapseButton.setEnabled(false);
			   }
			   if(e.getSource()== rolesCollapseButton){
				   collapseAll(rolesTree);
				   rolesCollapseButton.setEnabled(false);
			   }
			   if(e.getSource()== superRolesCollapseButton){
				   collapseAll(superRolesTree);
				   superRolesCollapseButton.setEnabled(false);
			   }
			   if(e.getSource()== groupsCollapseButton){
				   collapseAll(groupsTree);
				   groupsCollapseButton.setEnabled(false);
			   }
			   if(e.getSource()== superGroupsCollapseButton){
				   collapseAll(superGroupsTree);
				   superGroupsCollapseButton.setEnabled(false);
			   }
			   
		   }
	   }

	   //****************************Collapse -All**************************************
	   public void collapseAll(JTree tree) {
		    int row = tree.getRowCount() - 1;
		    while (row >= 0) {
		      tree.collapseRow(row);
		      row--;
		      }
		    }

	   
	   

	   // *********************** CLASS-SYNTAX*****************************************
	
	    @SuppressWarnings("unchecked")
		private ArrayList<String> getObjectsAssignedToResource(ResourceClassModel resource, int type){
	    	ArrayList<String> objects = new ArrayList<String>();
	    	if(type == ResourceClassModel.TYPE_ROLE||type==ResourceClassModel.TYPE_ORGUNIT){
	    		System.out.println(objectsAssignedListModel.size());
	    		for(int i=0;i<objectsAssignedListModel.size();i++){
	    			String currentObject = objectsAssignedListModel.get(i).toString();
	    			System.out.println(currentObject);
	    			Vector assignedClasses = getPetrinet().getResourceClassesResourceIsAssignedTo(currentObject);
					Object ass;
	    			for (Iterator iter = assignedClasses.iterator(); iter.hasNext();){
	    				ass = iter.next();
	    				if(ass.toString().equals(resource.toString())){
	    					objects.add(currentObject);
	    				}
				  		
					}
	    		}
	    		return objects;	
	    	}

	    	return objects;
	    }
	    
	   
	   @SuppressWarnings("unchecked")
		private boolean checkClassSyntax(String str){
	        boolean nameExists = false;
	    
	        if (str.equals("")){
	            JOptionPane.showMessageDialog(this, 
	            		Messages.getString("ResourceEditor.Error.EmptyResourceClass.Text"), 
	            		Messages.getString("ResourceEditor.Error.EmptyResourceClass.Title"),
	                    JOptionPane.ERROR_MESSAGE);
	            return false;
	        }

	        for (Iterator iter = getPetrinet().getOrganizationUnits().iterator(); !nameExists & iter.hasNext();){
	            if (iter.next().toString().equals(str)) 
	                nameExists = true;
	        }
	        for (Iterator iter = getPetrinet().getRoles().iterator(); !nameExists & iter.hasNext();){
	            if (iter.next().toString().equals(str)) 
	                nameExists = true;
	        }

	        if (nameExists){
	            JOptionPane.showMessageDialog(this, 
	            		Messages.getString("ResourceEditor.Error.DuplicateResourceClass.Text"), 
	            		Messages.getString("ResourceEditor.Error.DuplicateResourceClass.Title"),
	                    JOptionPane.ERROR_MESSAGE);
	            return false;
	        } 	       

	        return true;
	    }
	    
	    // Check if group is used by any transition
	    @SuppressWarnings("unchecked")
		private boolean groupIsUsed(String groupName)
	    {
	        boolean isUsed = false;
	               
	        HashMap<String, AbstractElementModel> alltrans = new HashMap<String, AbstractElementModel>();
	        alltrans.putAll(getPetrinet().getElementContainer().getElementsByType(AbstractPetriNetModelElement.TRANS_SIMPLE_TYPE));
	        alltrans.putAll(getPetrinet().getElementContainer().getElementsByType(AbstractPetriNetModelElement.TRANS_OPERATOR_TYPE));

	        for (Iterator transIter = alltrans.values().iterator(); transIter.hasNext() & !isUsed;)
	        {
	            TransitionModel transition = (TransitionModel)(transIter.next());
	            if (transition.getToolSpecific() != null &&
	                    transition.getToolSpecific().getTransResource() != null &&
	                    transition.getToolSpecific().getTransResource().getTransOrgUnitName() != null &&
	                    transition.getToolSpecific().getTransResource().getTransOrgUnitName().equals(groupName))
	            {
	                isUsed = true;
	            }
	         }
	        
	        return isUsed;
	    }

	    // Check if role is used by any transition
	    @SuppressWarnings("unchecked")
		private boolean roleIsUsed(String roleName)
	    {
	        boolean isUsed = false;
	        HashMap<String, AbstractElementModel> alltrans = new HashMap<String, AbstractElementModel>();
	        alltrans.putAll(getPetrinet().getElementContainer().getElementsByType(AbstractPetriNetModelElement.TRANS_SIMPLE_TYPE));
	        alltrans.putAll(getPetrinet().getElementContainer().getElementsByType(AbstractPetriNetModelElement.TRANS_OPERATOR_TYPE));
	        
	        for (Iterator transIter = alltrans.values().iterator(); transIter.hasNext() & !isUsed;)
	        {
	            TransitionModel transition = (TransitionModel)(transIter.next());
	            if (transition.getToolSpecific() != null &&
	                transition.getToolSpecific().getTransResource() != null &&
	                transition.getToolSpecific().getTransResource().getTransRoleName() != null &&
	                transition.getToolSpecific().getTransResource().getTransRoleName().equals(roleName))
	            {
	                isUsed = true;
	            }
	         }
	        
	        return isUsed;
	    }
	    
	    @SuppressWarnings("unchecked")
		private void updateRolesInPetrinet(String oldName, String newName)
	    {
	        HashMap<String, AbstractElementModel> alltrans = new HashMap<String, AbstractElementModel>();
	        alltrans.putAll(getPetrinet().getElementContainer().getElementsByType(AbstractPetriNetModelElement.TRANS_SIMPLE_TYPE));
	        alltrans.putAll(getPetrinet().getElementContainer().getElementsByType(AbstractPetriNetModelElement.TRANS_OPERATOR_TYPE));

	        for (Iterator transIter = alltrans.values().iterator(); transIter.hasNext();)
	        {
	            TransitionModel transition = (TransitionModel)(transIter.next());
	            if (transition.getToolSpecific() != null &&
	                    transition.getToolSpecific().getTransResource() != null &&
	                    transition.getToolSpecific().getTransResource().getTransRoleName() != null &&
	                    transition.getToolSpecific().getTransResource().getTransRoleName().equals(oldName))
	            {
	                transition.getToolSpecific().getTransResource().setTransRoleName(newName);
	            }
	        }    
	        
	        getPetrinet().replaceResourceClassMapping(oldName, newName);
	    }
	    
	    @SuppressWarnings("unchecked")
		private void updateGroupsInPetrinet(String oldName, String newName)
	    {
	        HashMap<String, AbstractElementModel> alltrans = new HashMap<String, AbstractElementModel>();
	        alltrans.putAll(getPetrinet().getElementContainer().getElementsByType(AbstractPetriNetModelElement.TRANS_SIMPLE_TYPE));
	        alltrans.putAll(getPetrinet().getElementContainer().getElementsByType(AbstractPetriNetModelElement.TRANS_OPERATOR_TYPE));

	        for (Iterator transIter = alltrans.values().iterator(); transIter.hasNext();)
	        {
	            TransitionModel transition = (TransitionModel)(transIter.next());
	            if (transition.getToolSpecific() != null &&
	                    transition.getToolSpecific().getTransResource() != null &&
	                    transition.getToolSpecific().getTransResource().getTransOrgUnitName() != null &&
	                    transition.getToolSpecific().getTransResource().getTransOrgUnitName().equals(oldName))
	            {
	                transition.getToolSpecific().getTransResource().setTransOrgUnitName(newName);
	            }
	        }   
	        
	        getPetrinet().replaceResourceClassMapping(oldName, newName);
	    }
	    
//! This rather important method will refresh the resource editor view
//! if the resources have changed in the Petri-Net model
//! It is called when the resource editor gains focus    
	    private void refreshFromModel()
	    {
	    	refreshRolesFromModel();
	    	refreshGroupsFromModel();
	    	refreshObjectsFromModel();
	    }
	    
	    
	    private void refreshRolesFromModel(){ 
	    	try{
	    		rolesListModel.removeAllElements();	 
	    		superRolesListModel.removeAllElements();
	    			for (int i = 0; i < getPetrinet().getRoles().size(); i++){
	    				rolesListModel.addElement((ResourceClassModel)getPetrinet().getRoles().get(i));	
	    			}
	    			for (int i = 0; i < getPetrinet().getRoles().size(); i++){
		    			if(getPetrinet().getRoles().get(i).getSuperModels()!=null){
		    				for(Iterator<ResourceClassModel> j = getPetrinet().getRoles().get(i).getSuperModels();j.hasNext();){
		    					ResourceClassModel superRole = j.next();
		    					if(!superRolesListModel.contains(superRole.toString())){
		    						superRolesListModel.addElement(superRole.toString());	
		    						for(int k=0;k<rolesListModel.size();k++){
		    							String role = rolesListModel.get(k).toString();
		    							if(role.equalsIgnoreCase(superRole.toString())){
		    								rolesListModel.remove(k);
		    							}
		    						}
		    					}
		    				}
		    			}
		    		}

		    		refreshRolesTreeFromListModel();
		    		refreshSuperRolesTreeFromListModel();
	    
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    }
	    
	    
	    private void refreshGroupsFromModel(){ 
	    	try{
	    		groupsListModel.removeAllElements();
	    		superGroupsListModel.removeAllElements();
	    		for (int i = 0; i < getPetrinet().getOrganizationUnits().size(); i++){
	    				groupsListModel.addElement((ResourceClassModel)getPetrinet().getOrganizationUnits().get(i));	
	    		}
	    		for (int i = 0; i < getPetrinet().getOrganizationUnits().size(); i++){
	    			if(getPetrinet().getOrganizationUnits().get(i).getSuperModels()!=null){
	    				for(Iterator<ResourceClassModel> j = getPetrinet().getOrganizationUnits().get(i).getSuperModels();j.hasNext();){
	    					ResourceClassModel superGroup = j.next();
	    					if(!superGroupsListModel.contains(superGroup.toString())){
	    						superGroupsListModel.addElement(superGroup.toString());	
	    						for(int k=0;k<groupsListModel.size();k++){
	    							String group = groupsListModel.get(k).toString();
	    							if(group.equalsIgnoreCase(superGroup.toString())){
	    								groupsListModel.remove(k);
	    							}
	    						}
	    					}
	    				}
	    			}
	    		}	    		
	    		refreshGroupsTreeFromListModel(); 
	    		refreshSuperGroupsTreeFromListModel();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    }
	    
	    @SuppressWarnings("unchecked")
		private void refreshObjectsFromModel(){
	    	try{
	    		objectsAssignedListModel.removeAllElements();
	    		objectsUnassignedListModel.removeAllElements(); 
	    		for (int i = 0; i < getPetrinet().getResources().size(); i++){
	    			objectsUnassignedListModel.addElement((ResourceModel) getPetrinet().getResources().get(i));
	    	    }
	    		int a = objectsUnassignedListModel.getSize()-1;
	    		for (int i = 0; i< (a+1) ;i++){
	    			ResourceModel currentObject = (ResourceModel) objectsUnassignedListModel.getElementAt(a-i);
	    			Vector assignedClasses = getPetrinet().getResourceClassesResourceIsAssignedTo(currentObject.toString());
	    			if (!assignedClasses.isEmpty() ){
	    				objectsUnassignedListModel.removeElement(currentObject);
	    				objectsAssignedListModel.addElement(currentObject);
	    				
	    			}

	    				
	    			Object ass;
	    			for (Iterator iter = assignedClasses.iterator(); iter.hasNext();){
	    				ass = iter.next();
	    				String currentResource = ass.toString();
	    				for(int j =0; j <  rolesTreeModel.getChildCount(rolesTopNode);j++){
	    					String currentRole = rolesTreeModel.getChild(rolesTopNode, j).toString();
	    					if(currentResource.equals(currentRole)){
	    						RolesTreeNode currentNode = (RolesTreeNode) rolesTreeModel.getChild(rolesTopNode, j);
	    						rolesTreeModel.insertNodeInto(new ObjectsTreeNode(currentObject.toString()), currentNode, currentNode.getChildCount());
	    					}
	    				}
	    				for(int j =0; j <  groupsTreeModel.getChildCount(groupsTopNode);j++){
	    					String currentGroup = groupsTreeModel.getChild(groupsTopNode, j).toString();
	    					if(currentResource.equals(currentGroup)){
	    						GroupsTreeNode currentNode = (GroupsTreeNode) groupsTreeModel.getChild(groupsTopNode, j);
	    						DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) groupsTree.getCellRenderer();
	    						renderer.setLeafIcon(Messages.getImageIcon("PetriNet.Resources.Delete"));
	    						groupsTreeModel.insertNodeInto(new ObjectsTreeNode(currentObject.toString()), currentNode, currentNode.getChildCount());
	    					
	    					}
	    				}
	                 
	              }
	    		}

	    		refreshObjectsTreeFromListModel();
	    	}catch (Exception e){
	    		e.printStackTrace();
	    	}
	    }
	    
	    

	    
	    private void refreshRolesTreeFromListModel(){
	    	removeAllNodesFromTreeModel(rolesTreeModel,rolesTopNode);
	    	for (int i = 0; i < rolesListModel.getSize();i++){
    			rolesTreeModel.insertNodeInto(new RolesTreeNode(rolesListModel.get(i).toString()), rolesTopNode, i);
    			
    		}
    		rolesTree.updateUI();
	    }
	    
	    private void refreshSuperRolesTreeFromListModel(){
	    	removeAllNodesFromTreeModel(superRolesTreeModel,superRolesTopNode);
	    	for (int i = 0; i < superRolesListModel.getSize();i++){
	    		String superRole = superRolesListModel.get(i).toString();
	    		SuperRolesTreeNode parent = new SuperRolesTreeNode(superRole);
    			superRolesTreeModel.insertNodeInto(parent, superRolesTopNode, i);
    			  			
    			for (int j = 0; j < getPetrinet().getRoles().size(); j++){
	    			if(getPetrinet().getRoles().get(j).getSuperModels()!=null){
	    				for(Iterator<ResourceClassModel> k = getPetrinet().getRoles().get(j).getSuperModels();k.hasNext();){
	    					ResourceClassModel currentSuperRole = k.next();
	    					if(currentSuperRole.toString().equalsIgnoreCase(superRole)){
	    						superRolesTreeModel.insertNodeInto(new SuperRolesTreeNode(getPetrinet().getRoles().get(j).toString()), parent, parent.getChildCount());				
	    					}
	    				}
	    			}
	    		}
    		}
    		superRolesTree.updateUI();
	    }
	    
	    private void refreshSuperGroupsTreeFromListModel(){
	    	removeAllNodesFromTreeModel(superGroupsTreeModel,superGroupsTopNode);
	    	for (int i = 0; i < superGroupsListModel.getSize();i++){
	    		String superGroup = superGroupsListModel.get(i).toString();
	    		SuperGroupsTreeNode parent = new SuperGroupsTreeNode(superGroup);
    			superGroupsTreeModel.insertNodeInto(parent, superGroupsTopNode, i);
    			  			
    			for (int j = 0; j < getPetrinet().getOrganizationUnits().size(); j++){
	    			if(getPetrinet().getOrganizationUnits().get(j).getSuperModels()!=null){
	    				for(Iterator<ResourceClassModel> k = getPetrinet().getOrganizationUnits().get(j).getSuperModels();k.hasNext();){
	    					ResourceClassModel currentSuperGroup = k.next();
	    					if(currentSuperGroup.toString().equalsIgnoreCase(superGroup )){
	    						superGroupsTreeModel.insertNodeInto(new SuperGroupsTreeNode(getPetrinet().getOrganizationUnits().get(j).toString()), parent, parent.getChildCount());				
	    					}
	    				}
	    			}
	    		}
    		}
    		superGroupsTree.updateUI();
	    }
	    
	    private void refreshGroupsTreeFromListModel(){
	    	removeAllNodesFromTreeModel(groupsTreeModel,groupsTopNode);
	    	for (int i = 0; i < groupsListModel.getSize();i++){
    			groupsTreeModel.insertNodeInto(new GroupsTreeNode(groupsListModel.get(i).toString()), groupsTopNode, i);
    			

    		}
    		groupsTree.updateUI();
	    }
	    
	    

	    private void refreshObjectsTreeFromListModel(){
	    	removeAllNodesFromTreeModel(objectsTreeModel,objectsAssignedNode);
	    	removeAllNodesFromTreeModel(objectsTreeModel,objectsUnassignedNode);
	    	for (int i = 0; i < objectsAssignedListModel.getSize();i++){
    			objectsTreeModel.insertNodeInto(new ObjectsTreeNode(objectsAssignedListModel.get(i).toString()), objectsAssignedNode, i);
    		}
	    	for (int i = 0; i < objectsUnassignedListModel.getSize();i++){
    			objectsTreeModel.insertNodeInto(new ObjectsTreeNode(objectsUnassignedListModel.get(i).toString()), objectsUnassignedNode, i);
    		}
    		objectsTree.updateUI();
	    }
	    
	    private void removeAllNodesFromTreeModel(DefaultTreeModel model,MutableTreeNode node){
	    	while(model.getChildCount(node)!=0)
	    		{
	    			MutableTreeNode node2remove = (MutableTreeNode) model.getChild(node, 0);
		    		model.removeNodeFromParent(node2remove);
	    		}
	    }

	
	    //++++++ResourceClassRenderer++++++++++
	    class MyRenderer1 extends DefaultTreeCellRenderer{
	    	public Component getTreeCellRendererComponent(JTree tree,Object value,boolean sel, boolean expanded,boolean leaf,int row, boolean hasFocus)
	    	{
	    		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
	    		DefaultMutableTreeNode currentTreeNode = (DefaultMutableTreeNode) value;

	    		if (currentTreeNode.isLeaf()&&currentTreeNode.getParent()==groupsTopNode) {
	    			setLeafIcon(resourceClass);
	    		} else 
	    			if(currentTreeNode.isLeaf()&&currentTreeNode.getParent()!=groupsTopNode){
	    				setLeafIcon(object);
	    			}	

	    		if	(currentTreeNode==objectsUnassignedNode){
	    			setIcon(unassignedIcon);
	    		}
	    		if (currentTreeNode.isLeaf()&&(currentTreeNode==objectsAssignedNode||currentTreeNode.getParent()==rolesTopNode||currentTreeNode.getParent()==groupsTopNode)) {
	    			setIcon(resourceClass);
	    		} else {
	    			if(currentTreeNode.isLeaf()){
	    				setLeafIcon(object);
	    			}
	    		}

	    		setClosedIcon(resourceClass);
			    setOpenIcon(resourceClass);			    
			    
	    		return this;
	    	}
	    }
	    MyRenderer1 rendererResourceClass = new MyRenderer1();
	    
	    //++++++ResourceSuperClassRenderer++++++++
	    class MyRenderer2 extends DefaultTreeCellRenderer{
	    	public Component getTreeCellRendererComponent(JTree tree,Object value,boolean sel, boolean expanded,boolean leaf,int row, boolean hasFocus)
	    	{
	    		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
	    		DefaultMutableTreeNode currentTreeNode = (DefaultMutableTreeNode) value;
	    		if (currentTreeNode.isLeaf()&&(currentTreeNode.getParent()==superGroupsTopNode||currentTreeNode.getParent()==superRolesTopNode)) {
	    			setIcon(resourceSuperClass);
	    		} else{ 
	    			if(currentTreeNode.isLeaf()){
	    				setIcon(resourceClass);
	    			}
	    		}
	    		setClosedIcon(resourceSuperClass);
			    setOpenIcon(resourceSuperClass);
	    		return this;
	    	}
	    }
	    
	    MyRenderer2 rendererResourceSuperClass = new MyRenderer2();
	    //++++++

}
	






@SuppressWarnings("serial")
class DragTree extends JTree {


	  private DefaultMutableTreeNode m_parent;

	  public DragTree(DefaultMutableTreeNode rootNode, DefaultMutableTreeNode parent) {
		  super( rootNode );
		  
		  m_parent = parent;
	    DragSource dragSource = DragSource.getDefaultDragSource();
	    dragSource
	        .createDefaultDragGestureRecognizer(this,
	            DnDConstants.ACTION_COPY_OR_MOVE,
	            new TreeDragGestureListener( this.getModel(),m_parent ) );
	  }

	  private static class TreeDragGestureListener implements DragGestureListener {
		  private TreeModel m_tree_model;
		  private DefaultMutableTreeNode m_parent;
		  
		  public TreeDragGestureListener( TreeModel tree_model, DefaultMutableTreeNode parent){
			  m_tree_model = tree_model;
			  m_parent = parent;
		  }
		  		  
	    public void dragGestureRecognized(DragGestureEvent dragGestureEvent) {
	      // Can only drag leafs
	      JTree tree = (JTree) dragGestureEvent.getComponent();
	      TreePath path = tree.getSelectionPath();
	      if (path == null) {
	        // Nothing selected, nothing to drag
	     
	        tree.getToolkit().beep();
	      } else {
	        DefaultMutableTreeNode selection = (DefaultMutableTreeNode) path
	            .getLastPathComponent();
	        if (selection.isLeaf() && (selection.getLevel() >= 2)) {
	          TransferableTreeNode node = new TransferableTreeNode(
	              selection);
	          dragGestureEvent.startDrag(DragSource.DefaultCopyDrop,
	              node, new MyDragSourceListener( selection,(DefaultTreeModel) m_tree_model, m_parent ));
	        } else {
	        
	          tree.getToolkit().beep();
	        }
	      }
	    }
	  }

	  private static class MyDragSourceListener implements DragSourceListener {
		  
		private DefaultMutableTreeNode m_node;
		private DefaultTreeModel m_model;
		private DefaultMutableTreeNode m_parent;
		
		public  MyDragSourceListener (DefaultMutableTreeNode node, DefaultTreeModel model, DefaultMutableTreeNode parent){
			  m_node = node;
			  m_model = model;
			  m_parent = parent;
		  }
		  
	    public void dragDropEnd(DragSourceDropEvent dragSourceDropEvent) {
	      if (dragSourceDropEvent.getDropSuccess()) {
	        int dropAction = dragSourceDropEvent.getDropAction();
	        if (dropAction == DnDConstants.ACTION_MOVE) {
	        	
	        	m_model.removeNodeFromParent( m_node );
	        	
	        	
	        	m_model.insertNodeInto(m_node, m_parent, m_parent.getChildCount());
	       	        	
	        
	        }
	      }
	    }

	    public void dragEnter(DragSourceDragEvent dragSourceDragEvent) {
	      DragSourceContext context = dragSourceDragEvent
	          .getDragSourceContext();
	      int dropAction = dragSourceDragEvent.getDropAction();
	      if ((dropAction & DnDConstants.ACTION_COPY) != 0) {
	        context.setCursor(DragSource.DefaultCopyDrop);
	      } else if ((dropAction & DnDConstants.ACTION_MOVE) != 0) {
	        context.setCursor(DragSource.DefaultMoveDrop);
	      } else {
	        context.setCursor(DragSource.DefaultCopyNoDrop);
	      }
	    }

	    public void dragExit(DragSourceEvent dragSourceEvent) {
	    }

	    public void dragOver(DragSourceDragEvent dragSourceDragEvent) {
	    }

	    public void dropActionChanged(DragSourceDragEvent dragSourceDragEvent) {
	    }
	  }
	}

	@SuppressWarnings("serial")
	class TransferableTreeNode extends DefaultMutableTreeNode implements
	    Transferable {
	  final static int TREE = 0;


	  final public static DataFlavor DEFAULT_MUTABLE_TREENODE_FLAVOR = new DataFlavor(
	      DefaultMutableTreeNode.class, "Default Mutable Tree Node");

	  static DataFlavor flavors = DEFAULT_MUTABLE_TREENODE_FLAVOR ;


	  private DefaultMutableTreeNode data;

	  public TransferableTreeNode(DefaultMutableTreeNode data) {
	    this.data = data;
	  }

	  public DataFlavor isDataFlavorSupported() {
	    return flavors;
	  }

	  public Object getTransferData(DataFlavor flavor)
	      throws UnsupportedFlavorException, IOException {
	    Object returnObject;
	    if (flavor.equals(flavors)) {
	      Object userObject = data.getUserObject();
	      if (userObject == null) {
	        returnObject = data;
	      } else {
	        returnObject = userObject;
	      }
	    } 
	    else {
	      throw new UnsupportedFlavorException(flavor);
	    }
	    return returnObject;
	  }



	  public boolean isDataFlavorSupported(DataFlavor flavor) {
	    boolean returnValue = false;
	      if (flavor.equals(flavors)){
	        returnValue = true;
	      }
	    
	    return returnValue;
	  }


	public DataFlavor[] getTransferDataFlavors() {
		
		return null;
	}


	}
	
	@SuppressWarnings("serial")
	class DropTree extends JTree implements Autoscroll {

		  private Insets insets;
		  PetriNetModelProcessor petrinet;

		  private int top = 0, bottom = 0, topRow = 0, bottomRow = 0;

		  public DropTree(DefaultMutableTreeNode rootNode,PetriNetModelProcessor petrinet ) {
			  super( rootNode );
			  this.petrinet=petrinet;
		      @SuppressWarnings("unused")
			DropTarget dropTarget = new DropTarget(this,
		         new TreeDropTargetListener());
		  }



		  public Insets getAutoscrollInsets() {
		    return insets;
		  }

		  public void autoscroll(Point p) {
		    // Only support up/down scrolling
		    top = Math.abs(getLocation().y) + 10;
		    bottom = top + getParent().getHeight() - 20;
		    int next;
		    if (p.y < top) {
		      next = topRow--;
		      bottomRow++;
		      scrollRowToVisible(next);
		    } else if (p.y > bottom) {
		      next = bottomRow++;
		      topRow--;
		      scrollRowToVisible(next);
		    }
		  }


		  private class TreeDropTargetListener implements DropTargetListener {

		    public void dragEnter(DropTargetDragEvent dropTargetDragEvent) {
		      // Setup positioning info for auto-scrolling
		      top = Math.abs(getLocation().y);
		      bottom = top + getParent().getHeight();
		      topRow = getClosestRowForLocation(0, top);
		      bottomRow = getClosestRowForLocation(0, bottom);
		      insets = new Insets(top + 10, 0, bottom - 10, getWidth());
		    }

		    public void dragExit(DropTargetEvent dropTargetEvent) {
		    }

		    public void dragOver(DropTargetDragEvent dropTargetDragEvent) {
		    }

		    public void dropActionChanged(DropTargetDragEvent dropTargetDragEvent) {
		    }

		    public synchronized void drop(DropTargetDropEvent dropTargetDropEvent) {
		      // Only support dropping over nodes that aren't leafs

		      Point location = dropTargetDropEvent.getLocation();
		      TreePath path = getPathForLocation(location.x, location.y);
		      Object node = path.getLastPathComponent();

		      if ((node != null) && (node instanceof TreeNode))//&& (!((TreeNode) node).isLeaf())) 
		    		  {
		        try {
		          Transferable tr = dropTargetDropEvent.getTransferable();
		          if (tr
		              .isDataFlavorSupported(TransferableTreeNode.DEFAULT_MUTABLE_TREENODE_FLAVOR)) {
		            dropTargetDropEvent
		                .acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
		            Object userObject = tr
		                .getTransferData(TransferableTreeNode.DEFAULT_MUTABLE_TREENODE_FLAVOR);
		            addElement(path, userObject);
		            dropTargetDropEvent.dropComplete(true);
		          } 
		          else {
		           
		            dropTargetDropEvent.rejectDrop();
		          }
		        } catch (IOException io) {
		          io.printStackTrace();
		          dropTargetDropEvent.rejectDrop();
		        } catch (UnsupportedFlavorException ufe) {
		          ufe.printStackTrace();
		          dropTargetDropEvent.rejectDrop();
		        }
		      } else {
		       
		        dropTargetDropEvent.rejectDrop();
		      }
		    }
		    
			   private boolean parentContainsNode(MutableTreeNode parent, MutableTreeNode node){
				   boolean contains= false;
					  for(int j = 0;j<parent.getChildCount();j++){
						  MutableTreeNode child = (MutableTreeNode) parent.getChildAt(j);
						  if(node.toString().equalsIgnoreCase(child.toString())){
							  contains = true;
						  break;}
					  }
				   return contains;
			   }
			   
		    private void addElement(TreePath path, Object element) {
		      DefaultMutableTreeNode parent = (DefaultMutableTreeNode) path
		          .getLastPathComponent();
		      DefaultMutableTreeNode node = new DefaultMutableTreeNode(element);
		  
		      DefaultTreeModel model = (DefaultTreeModel) (DropTree.this
		          .getModel());
		      
		      int d =  parent.getLevel();
		     
		      if(!(d > 1))
		      		if (!parentContainsNode(parent,node)){
		    	
		      			petrinet.addResourceMapping(parent.toString(), node.toString());		
		      			model.insertNodeInto(node, parent, parent.getChildCount());
		      			
		      			DefaultMutableTreeNode superParent = (DefaultMutableTreeNode) parent.getParent();
		      			
		      			int pathToExpand = superParent.getIndex(parent);
		      			DropTree.this.expandRow(pathToExpand);
		      		}
		      		else{
		      			JOptionPane.showMessageDialog(null , Messages.getString("ResourceEditor.Error.AlreadyAssigned.Text"), Messages.getString("ResourceEditor.Error.AlreadyAssigned.Title"),
	                        JOptionPane.ERROR_MESSAGE);
		      		}
		      else{
		    	 DefaultMutableTreeNode  superParent = (DefaultMutableTreeNode) parent.getParent();
		      		if (!parentContainsNode(superParent,node)){
				    	
		      			petrinet.addResourceMapping(superParent.toString(), node.toString());		
		      			model.insertNodeInto(node, superParent, superParent.getChildCount());
		      			
		      			
		      		}
		      		else{
		      			JOptionPane.showMessageDialog(null , Messages.getString("ResourceEditor.Error.AlreadyAssigned.Text"), Messages.getString("ResourceEditor.Error.AlreadyAssigned.Title"),
	                        JOptionPane.ERROR_MESSAGE);
		      		}
		      }
		    }
		  }
	  
}



	   
	   
