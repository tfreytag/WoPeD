package org.woped.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JSeparator;

import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.JCommandMenuButton;
import org.pushingpixels.flamingo.api.common.RichTooltip;
import org.pushingpixels.flamingo.api.common.popup.JCommandPopupMenu;
import org.pushingpixels.flamingo.api.common.popup.JPopupPanel;
import org.pushingpixels.flamingo.api.common.popup.PopupPanelCallback;
import org.pushingpixels.flamingo.api.ribbon.JRibbonBand;
import org.pushingpixels.flamingo.api.ribbon.JRibbonComponent;
import org.pushingpixels.flamingo.api.ribbon.JRibbonFrame;
import org.pushingpixels.flamingo.api.ribbon.RibbonContextualTaskGroup;
import org.pushingpixels.flamingo.api.ribbon.RibbonElementPriority;
import org.pushingpixels.flamingo.api.ribbon.RibbonTask;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizeSequencingPolicies;
import org.woped.core.config.ConfigurationManager;
import org.woped.core.controller.AbstractApplicationMediator;
import org.woped.core.controller.AbstractViewEvent;
import org.woped.core.controller.IEditor;
import org.woped.core.gui.IUserInterface;
import org.woped.core.qualanalysis.IReachabilityGraph;
import org.woped.core.utilities.LoggerManager;
import org.woped.editor.action.WoPeDAction;
import org.woped.editor.controller.ActionFactory;
import org.woped.editor.controller.VisualController;
import org.woped.editor.controller.vep.ViewEvent;
import org.woped.gui.icons.*;
import org.woped.translations.Messages;
import org.woped.config.general.WoPeDRecentFile;

public class MainFrame extends JRibbonFrame implements IUserInterface {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RibbonContextualTaskGroup		tokengameGroup				= null;
	
	private JCommandButton 					taskbarButtonNew			= null;
	private JCommandButton 					taskbarButtonSave			= null;
	private JCommandButton 					taskbarButtonClose			= null;
	private JCommandButton 					taskbarButtonPaste			= null;
	private JCommandButton 					taskbarButtonCopy			= null;
	private JCommandButton 					taskbarButtonUndo			= null;
	private JCommandButton 					taskbarButtonRedo			= null;
	
	private	JRibbonBand 					saveBand					= null;
	private	JRibbonBand 					documentBand				= null;
	private	JRibbonBand 					outputBand					= null;
	private	JRibbonBand 					apromoreBand				= null;
	private	JRibbonBand 					editBand					= null;
	private	JRibbonBand 					formsBand					= null;
	private	JRibbonBand 					layoutBand					= null;
	private	JRibbonBand 					windowsBand					= null;
	private	JRibbonBand 					sidebarBand					= null;
	private	JRibbonBand 					analyzeBand					= null;
	private	JRibbonBand 					metricsBand					= null;
	private JRibbonBand 					optionsAndHelpBand			= null;
	private	JRibbonBand 					tokengamePlayBand			= null;
	private	JRibbonBand 					tokengameEditBand			= null;
	
	private RibbonTask						fileTask					= null;
	private RibbonTask						editTask					= null;
	private RibbonTask						viewTask					= null;
	private RibbonTask						analyzeTask					= null;
	private RibbonTask						optionsHelpTask				= null;
	private RibbonTask						tokengameTask				= null;

	private	JCommandButton 					newButton					= null; 
	private	JCommandButton 					openButton					= null; 
	private	JCommandButton 					recentButton				= null; 
	private	JCommandButton 					closeButton					= null; 
	
	private	JCommandButton 					saveButton					= null; 
	private	JCommandButton 					saveAsButton				= null; 
	
	private	JCommandButton 					printButton					= null; 
	private	JCommandButton 					exportAsButton				= null; 

	private	JCommandButton 					importApromoreButton		= null; 
	private	JCommandButton 					exportApromoreButton		= null; 
	
	private	JCommandButton 					undoButton					= null; 
	private	JCommandButton 					redoButton					= null; 
	private	JCommandButton 					cutButton					= null; 
	private	JCommandButton 					copyButton					= null; 
	private	JCommandButton 					pasteButton					= null; 
	private	JCommandButton 					groupButton					= null; 
	private	JCommandButton 					ungroupButton				= null; 
	
	private	JCommandButton 					placeButton					= null; 
	private	JCommandButton 					transitionButton			= null; 
	private	JCommandButton 					xorSplitButton				= null; 
	private	JCommandButton 					xorJoinButton				= null; 
	private	JCommandButton 					andSplitButton				= null; 
	private	JCommandButton 					andJoinButton				= null; 
	private	JCommandButton 					xorSplitJoinButton			= null; 
	private	JCommandButton 					andJoinXorSplitButton		= null; 
	private	JCommandButton 					xorJoinAndSplitButton		= null; 
	private	JCommandButton 					andSplitJoinButton			= null; 
	private	JCommandButton 					subprocessButton			= null; 
	
	private	JCommandButton 					changeOrientationButton 	= null;
	private	JCommandButton 					optimizeLayoutButton 		= null;

	private	JCommandButton 					arrangeButton 				= null;
	private	JCommandButton 					cascadeButton 				= null;
	
	private JRibbonComponent				overviewComponent			= null;
	private JRibbonComponent				treeviewComponent			= null;
	private JCheckBox						overviewCheckbox			= null;
	private JCheckBox						treeviewCheckbox			= null;

	private	JCommandButton 					tokengameStartButton		= null;
	private	JCommandButton 					coverabilityGraphButton 	= null;
	private	JCommandButton 					coloringButton 				= null;
	private	JCommandButton 					semanticalAnalysisButton 	= null;
	private	JCommandButton 					capacityPlanningButton 		= null;
	private	JCommandButton 					quantitativeSimulationButton= null;

	private	JCommandButton 					processMetricsButton 		= null;
	private	JCommandButton 					processMassAnalyzeButton 	= null;
	private	JCommandButton 					processMetricsBuilderButton = null;

	private	JCommandButton 					configurationButton 		= null;
	private	JCommandButton 					manualButton 				= null;
	private	JCommandButton 					contentsButton 				= null;
	private	JCommandButton 					sampleNetsButton 			= null;
	private	JCommandButton 					reportBugButton 			= null;
	private	JCommandButton 					aboutButton 				= null;

	private	JCommandButton 					skipBackwardButton 			= null;
	private	JCommandButton 					seekBackwardButton 			= null;
	private	JCommandButton 					jumpIntoSubProcessButton 	= null;
	private	JCommandButton 					pauseButton 				= null;
	private	JCommandButton 					playButton 					= null;
	private	JCommandButton 					stopButton 					= null;
	private	JCommandButton 					jumpOutOfSubprocessButton 	= null;
	private	JCommandButton 					seekForwardButton 			= null;
	private	JCommandButton 					skipForwardButton 			= null;

	private	JCommandButton 					stepByStepButton 			= null;
	private	JCommandButton 					autoPlayButton 				= null;
//	private	JCommandButton 					randomButton 				= null;
	private	JCommandButton 					tokengameCloseButton 		= null;

	private ActionButtonListener			newListener					= null;
	private ActionButtonListener			tokengameStartListener		= null;
	private ActionButtonListener			tokengameCloseListener		= null;
	
	private JCommandPopupMenu              	m_sampleMenu             	= null;
    private JCommandPopupMenu			   	m_recentMenu				= null;
    
    private AbstractApplicationMediator	   	m_mediator 					= null;   

     	
     // ActionListener for Ribbon Components
    class ActionButtonListener implements ActionListener {
		
		private int			event_id;
		private String      action_id;
		private WoPeDAction	action;
				
		public ActionButtonListener(AbstractApplicationMediator mediator, String action_id, int event_id, JComponent target) {
			
			action = ActionFactory.getStaticAction(action_id);
			ActionFactory.addTarget(mediator, action_id, target);
			this.event_id = event_id;
			this.action_id = action_id;
			target.setName(action_id);
		}
		
		public void actionPerformed(ActionEvent e) {	
			
			action.actionPerformed(new ViewEvent(this, AbstractViewEvent.VIEWEVENTTYPE_GUI, event_id));
			if (action_id.equals(ActionFactory.ACTIONID_CLOSE_TOKENGAME)) {
				getRibbon().setVisible(getTokengameGroup(), false);
				getRibbon().setSelectedTask(getAnalyzeTask());
			}
		}
	}

	class recentFileListener implements ActionListener {
		
		private int numEditors = m_mediator.getUi().getAllEditors().size();
		private String path;
		
		public recentFileListener(String path) {
			this.path = path;
		}
		
		public void actionPerformed(ActionEvent e) {
			
			File f = new File(path);
            fireViewEvent(new ViewEvent(m_mediator.getUi(), AbstractViewEvent.VIEWEVENTTYPE_FILE, AbstractViewEvent.OPEN, f));		
	    	if (m_mediator.getUi().getAllEditors().size() > numEditors)
	    		getRibbon().setSelectedTask(getEditTask());
		}		
	}
    
	class sampleFileListener implements ActionListener {
		
		private String path;
		
		public sampleFileListener(String path) {
			this.path = path;
		}
		
		public void actionPerformed(ActionEvent e) {
			File f = new File(path);
            fireViewEvent(new ViewEvent(m_mediator.getUi(), AbstractViewEvent.VIEWEVENTTYPE_FILE, AbstractViewEvent.OPEN_SAMPLE, f));		
    		getRibbon().setSelectedTask(getEditTask());
		}
	}
	
    public MainFrame() {	
    	
		super();
		setApplicationIcon(new logo_woped());
		setTitle("WoPeD Version " + Messages.getString("Application.Version"));
	}
	
	public void initialize(AbstractApplicationMediator mediator) {
		
		setMediator(mediator);
				
		getRibbon().addTaskbarComponent(getTaskbarButtonNew());
		getRibbon().addTaskbarComponent(getTaskbarButtonSave());
		getRibbon().addTaskbarComponent(getTaskbarButtonClose());		
		getRibbon().addTaskbarComponent(new JSeparator(JSeparator.VERTICAL));
		getRibbon().addTaskbarComponent(getTaskbarButtonPaste());
		getRibbon().addTaskbarComponent(getTaskbarButtonCopy());
		getRibbon().addTaskbarComponent(new JSeparator(JSeparator.VERTICAL));
		getRibbon().addTaskbarComponent(getTaskbarButtonUndo());		
		getRibbon().addTaskbarComponent(getTaskbarButtonRedo());		

		getRibbon().addTask(getFileTask());
		getRibbon().addTask(getEditTask());
		getRibbon().addTask(getAnalyzeTask());
		getRibbon().addTask(getViewTask());
		getRibbon().addTask(getOptionsHelpTask());
		getRibbon().addContextualTaskGroup(getTokengameGroup());

		VisualController.getInstance().propertyChange(new PropertyChangeEvent(mediator, "InternalFrameCount", null, null));
	}

	
	private void setTooltip(JCommandButton button, String prefix) {
		button.setActionRichTooltip(new RichTooltip(Messages.getString(prefix + ".text"), Messages.getString(prefix + ".tooltip")));
	}

	
	private void setPopupTooltip(JCommandButton button, String prefix) {
		button.setPopupRichTooltip(new RichTooltip(Messages.getString(prefix + ".text"), Messages.getString(prefix + ".tooltip")));
	}

	
	/*************/
	/* TASKGROUP */
	/*************/
	private RibbonContextualTaskGroup getTokengameGroup() {
		
		if (tokengameGroup == null) {
			tokengameGroup = new RibbonContextualTaskGroup("", Color.green, getTokengameTask());
		}
		
		return tokengameGroup;
	}

	/***********/
	/* TASKBAR */
	/***********/
	private JCommandButton getTaskbarButtonNew() {
		
		if (taskbarButtonNew == null) {
			taskbarButtonNew = new JCommandButton("",new document_new());
			setTooltip(taskbarButtonNew, "Document.new");
			taskbarButtonNew.addActionListener(new ActionButtonListener(m_mediator, ActionFactory.ACTIONID_NEW, AbstractViewEvent.NEW, taskbarButtonNew));
		}
		return taskbarButtonNew;
	}

	private JCommandButton getTaskbarButtonSave() {
		
		if (taskbarButtonSave == null) {
			taskbarButtonSave = new JCommandButton("",new document_save());
			setTooltip(taskbarButtonSave, "Save.save");
			taskbarButtonSave.addActionListener(new ActionButtonListener(m_mediator, ActionFactory.ACTIONID_SAVE, AbstractViewEvent.SAVE, taskbarButtonSave));
		}
		return taskbarButtonSave;
	}
	
	private JCommandButton getTaskbarButtonClose() {
		
		if (taskbarButtonClose == null) {
			taskbarButtonClose = new JCommandButton("",new file_close());
			setTooltip(taskbarButtonClose, "Document.close");
			taskbarButtonClose.addActionListener(new ActionButtonListener(m_mediator, ActionFactory.ACTIONID_CLOSE, AbstractViewEvent.CLOSE, taskbarButtonClose));
		}
		return taskbarButtonClose;
	}
	
	private JCommandButton getTaskbarButtonPaste() {
		
		if (taskbarButtonPaste == null) {
			taskbarButtonPaste = new JCommandButton("",new edit_paste());
			taskbarButtonPaste.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_PASTE, AbstractViewEvent.PASTE, taskbarButtonPaste));
		}
		return taskbarButtonPaste;
	}
	
	private JCommandButton getTaskbarButtonCopy() {
		
		if (taskbarButtonCopy == null) {
			taskbarButtonCopy = new JCommandButton("",new edit_copy());
			taskbarButtonCopy.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_COPY, AbstractViewEvent.COPY, taskbarButtonCopy));	
		}
		return taskbarButtonCopy;
	}
	
	private JCommandButton getTaskbarButtonUndo() {
		
		if (taskbarButtonUndo == null) {
			taskbarButtonUndo = new JCommandButton("",new editor_undo());
			taskbarButtonUndo.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_UNDO, AbstractViewEvent.UNDO, taskbarButtonUndo));
		}
		return taskbarButtonUndo;
	}

	private JCommandButton getTaskbarButtonRedo() {
		
		if (taskbarButtonRedo == null) {
			taskbarButtonRedo = new JCommandButton("",new editor_redo());
			taskbarButtonRedo.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_REDO, AbstractViewEvent.REDO, taskbarButtonRedo));
		}
		return taskbarButtonRedo;
	}
			
			
	/*********/
	/* TASKS */
	/*********/
	private RibbonTask getFileTask() {
		
		if (fileTask == null) {
			if (ConfigurationManager.getConfiguration().getApromoreUse())
				fileTask = new RibbonTask(Messages.getTitle("Task.File"), getDocumentBand(), getSaveBand(), getOutputBand(), getApromoreBand());	
			else
				fileTask = new RibbonTask(Messages.getTitle("Task.File"), getDocumentBand(), getSaveBand(), getOutputBand());						
			fileTask.setResizeSequencingPolicy(new CoreRibbonResizeSequencingPolicies.CollapseFromLast(fileTask));
		}
		
		return fileTask;
	}
	
	private RibbonTask getEditTask() {
		
		if (editTask == null) {
			editTask = new RibbonTask(Messages.getTitle("Task.Edit"), getEditBand(), getFormsBand());
			editTask.setResizeSequencingPolicy(new CoreRibbonResizeSequencingPolicies.CollapseFromLast(editTask));
		}
		
		return editTask;
	}
	
	private RibbonTask getAnalyzeTask() {
		
		if (analyzeTask == null) {
			if (ConfigurationManager.getConfiguration().isUseMetrics())
				analyzeTask = new RibbonTask(Messages.getTitle("Task.Analyze"), getAnalyzeBand(), getMetricsBand());	
			else
				analyzeTask = new RibbonTask(Messages.getTitle("Task.Analyze"), getAnalyzeBand());					
			analyzeTask.setResizeSequencingPolicy(new CoreRibbonResizeSequencingPolicies.CollapseFromLast(analyzeTask));
		}
		
		return analyzeTask;
	}
	
	private RibbonTask getViewTask() {
		
		if (viewTask == null) {
			viewTask = new RibbonTask(Messages.getTitle("Task.View"), getLayoutBand(), getWindowsBand(), getSidebarBand());	
			viewTask.setResizeSequencingPolicy(new CoreRibbonResizeSequencingPolicies.CollapseFromLast(viewTask));
		}
		
		return viewTask;
	}
	
	private RibbonTask getOptionsHelpTask() {
		
		if (optionsHelpTask == null) {
			optionsHelpTask = new RibbonTask(Messages.getTitle("Task.OptionsHelp"), getOptionsHelpBand());
			optionsHelpTask.setResizeSequencingPolicy(new CoreRibbonResizeSequencingPolicies.CollapseFromLast(optionsHelpTask));
		}
		
		return optionsHelpTask;
	}
	
	private RibbonTask getTokengameTask() {
		
		if (tokengameTask == null) {
			tokengameTask = new RibbonTask(Messages.getTitle("Task.Tokengame"), getTokengamePlayBand(), getTokengameEditBand());
			tokengameTask.setResizeSequencingPolicy(new CoreRibbonResizeSequencingPolicies.CollapseFromLast(tokengameTask));
		}
		
		return tokengameTask;
	}
	
	/*********/
	/* BANDS */
	/*********/
	private JRibbonBand getDocumentBand () {
		
		if (documentBand == null) {	
			documentBand = new JRibbonBand(Messages.getString("Document.textBandTitle"), null);
			documentBand.setResizePolicies(CoreRibbonResizePolicies.getCorePoliciesNone(documentBand));
					
			documentBand.addCommandButton(getNewButton(),  RibbonElementPriority.TOP);
			documentBand.addCommandButton(getOpenButton(),  RibbonElementPriority.TOP);
			documentBand.addCommandButton(getRecentButton(), RibbonElementPriority.TOP);
			documentBand.addCommandButton(getCloseButton(), RibbonElementPriority.TOP);
		}	
		
		return documentBand;
	}

	private JRibbonBand getSaveBand() {
		
		if (saveBand == null) {
			saveBand = new JRibbonBand(Messages.getString("Save.textBandTitle"), null);
			saveBand.setResizePolicies(CoreRibbonResizePolicies.getCorePoliciesNone(saveBand));
			saveBand.addCommandButton(getSaveButton(), RibbonElementPriority.TOP);
			saveBand.addCommandButton(getSaveAsButton(), RibbonElementPriority.TOP);
		}
		
		return saveBand;
	}
	
	private JRibbonBand getOutputBand() {
		
		if (outputBand == null) {
			outputBand = new JRibbonBand(Messages.getString("DataOutput.textBandTitle"), null);
			outputBand.setResizePolicies(CoreRibbonResizePolicies.getCorePoliciesNone(outputBand));
			outputBand.addCommandButton(getPrintButton(), RibbonElementPriority.TOP);
			outputBand.addCommandButton(getExportButton(), RibbonElementPriority.TOP);
		}
		
		return outputBand;
	}
	
	private JRibbonBand getApromoreBand() {
		
		if (apromoreBand == null) {
			apromoreBand = new JRibbonBand(Messages.getString("Apromore.textBandTitle"), null);
			apromoreBand.setResizePolicies(CoreRibbonResizePolicies.getCorePoliciesNone(apromoreBand));
			apromoreBand.addCommandButton(getImportApromoreButton(),  RibbonElementPriority.TOP);
			apromoreBand.addCommandButton(getExportApromoreButton(),  RibbonElementPriority.TOP);
		}
		
		return apromoreBand;
	}
			
	private JRibbonBand getEditBand() {
		
		if (editBand == null) {
			editBand = new JRibbonBand(Messages.getString("Edit.textBandTitle"), null);
			editBand.setResizePolicies(CoreRibbonResizePolicies.getCorePoliciesNone(editBand));
			editBand.addCommandButton(getUndoButton(),  RibbonElementPriority.TOP);
			editBand.addCommandButton(getRedoButton(),  RibbonElementPriority.TOP);
			editBand.addCommandButton(getCutButton(),  RibbonElementPriority.TOP);
			editBand.addCommandButton(getCopyButton(),  RibbonElementPriority.TOP);
			editBand.addCommandButton(getPasteButton(),  RibbonElementPriority.TOP);
			editBand.addCommandButton(getGroupButton(),  RibbonElementPriority.TOP);
			editBand.addCommandButton(getUngroupButton(),  RibbonElementPriority.TOP);
		}
		
		return editBand;
	}
	
	private JRibbonBand getFormsBand() {
			
		if (formsBand == null) {
			formsBand = new JRibbonBand(Messages.getString("Forms.textBandTitle"), new forms_place());
			formsBand.setResizePolicies(CoreRibbonResizePolicies.getCorePoliciesNone(formsBand));
		
			formsBand.addCommandButton(getPlaceButton(), RibbonElementPriority.MEDIUM);
			formsBand.addCommandButton(getTransitionButton(), RibbonElementPriority.MEDIUM);
			formsBand.addCommandButton(getAndSplitButton(), RibbonElementPriority.MEDIUM);
			formsBand.addCommandButton(getXorSplitButton(), RibbonElementPriority.MEDIUM);
			formsBand.addCommandButton(getAndJoinButton(), RibbonElementPriority.MEDIUM);
			formsBand.addCommandButton(getXorJoinButton(), RibbonElementPriority.MEDIUM);
			formsBand.addCommandButton(getXorSplitJoinButton(), RibbonElementPriority.MEDIUM);
			formsBand.addCommandButton(getAndSplitJoinButton(), RibbonElementPriority.MEDIUM);
			formsBand.addCommandButton(getAndJoinXorSplitButton(), RibbonElementPriority.MEDIUM);
			formsBand.addCommandButton(getXorJoinAndSplitButton(), RibbonElementPriority.MEDIUM);
			formsBand.addCommandButton(getSubprocessButton(), RibbonElementPriority.MEDIUM);
		}
		
		return formsBand;
	}

	private JRibbonBand getLayoutBand() {
		
		if (layoutBand == null) {
			layoutBand = new JRibbonBand(Messages.getString("View.textBandTitle"), new editor_group());
			layoutBand.setResizePolicies(CoreRibbonResizePolicies.getCorePoliciesNone(layoutBand));
			layoutBand.startGroup();

			layoutBand.addCommandButton(getChangeOrientationButton(),RibbonElementPriority.TOP);
			layoutBand.addCommandButton(getOptimizeLayoutButton(),RibbonElementPriority.TOP);
		}
		
		return layoutBand;
	}

	private JRibbonBand getAnalyzeBand() {
		
		if (analyzeBand == null) {	
			analyzeBand = new JRibbonBand(Messages.getString("Tools.textBandTitle"), new analyze_semanticalanalysis());
			analyzeBand.setResizePolicies(CoreRibbonResizePolicies.getCorePoliciesNone(analyzeBand));
			analyzeBand.startGroup();

			analyzeBand.addCommandButton(getTokengameStartButton(), RibbonElementPriority.TOP);
			analyzeBand.addCommandButton(getColoringButton(), RibbonElementPriority.TOP);
			analyzeBand.addCommandButton(getSemanticalAnalysisButton(), RibbonElementPriority.TOP);
			analyzeBand.addCommandButton(getCapacityPlanningButton(), RibbonElementPriority.TOP);
			analyzeBand.addCommandButton(getQuantitativeSimulationButton(),RibbonElementPriority.TOP);
			analyzeBand.addCommandButton(getCoverabilityGraphButton(), RibbonElementPriority.TOP);
		}
		
		return analyzeBand;
	}

	private JRibbonBand getMetricsBand() {

		if (metricsBand == null) {
			metricsBand = new JRibbonBand(Messages.getString("Metrics.textBandTitle"), new analyze_semanticalanalysis());
			metricsBand.setResizePolicies(CoreRibbonResizePolicies.getCorePoliciesNone(metricsBand));
			metricsBand.startGroup();

			metricsBand.addCommandButton(getProcessMetricsButton(), RibbonElementPriority.TOP);
			metricsBand.addCommandButton(getProcessMassAnalyzeButton(), RibbonElementPriority.TOP);
			metricsBand.addCommandButton(getProcessMetricsBuilderButton(), RibbonElementPriority.TOP);
		}

		return metricsBand;
	}
	
	private JRibbonBand getWindowsBand (){
			
		if (windowsBand == null) {	
			windowsBand = new JRibbonBand(Messages.getString("WindowPreferences.textBandTitle"), null);
			windowsBand.setResizePolicies(CoreRibbonResizePolicies.getCorePoliciesNone(windowsBand));
		
			windowsBand.addCommandButton(getCascadeButton(), RibbonElementPriority.TOP);
			windowsBand.addCommandButton(getArrangeButton(), RibbonElementPriority.TOP);
		}
		
		return windowsBand;
	}
	
	private JRibbonBand getSidebarBand() {
		
		if (sidebarBand == null) {	
			sidebarBand = new JRibbonBand(Messages.getString("ShowHide.textBandTitle"),new format_justify_left(), null);
			sidebarBand.setResizePolicies(CoreRibbonResizePolicies.getCorePoliciesNone(sidebarBand));

			sidebarBand.addRibbonComponent(getOverviewComponent());
			sidebarBand.addRibbonComponent(getTreeviewComponent());
		}
		
		return sidebarBand;
	}

	private JRibbonBand getOptionsHelpBand() {

		if (optionsAndHelpBand == null) {
			optionsAndHelpBand = new JRibbonBand(
					Messages.getString("OptionsAndHelp.textBandTitle"), null);
			optionsAndHelpBand.setResizePolicies(CoreRibbonResizePolicies
					.getCorePoliciesNone(optionsAndHelpBand));
			optionsAndHelpBand.startGroup();

			optionsAndHelpBand.addCommandButton(getConfigurationButton(),
					RibbonElementPriority.TOP);
			optionsAndHelpBand.addCommandButton(getManualButton(),
					RibbonElementPriority.TOP);
			optionsAndHelpBand.addCommandButton(getContentsButton(),
					RibbonElementPriority.TOP);
			optionsAndHelpBand.addCommandButton(getSampleNetsButton(),
					RibbonElementPriority.TOP);
			optionsAndHelpBand.addCommandButton(getReportBugButton(),
					RibbonElementPriority.TOP);
			optionsAndHelpBand.addCommandButton(getAboutButton(),
					RibbonElementPriority.TOP);
		}
		return optionsAndHelpBand;
	}
	
	private JRibbonBand getTokengamePlayBand (){
		
		if (tokengamePlayBand == null) {	
			tokengamePlayBand = new JRibbonBand(Messages.getString("tgPlayBand.textBandTitle"),new tokengame_play_start());
			tokengamePlayBand.setResizePolicies(CoreRibbonResizePolicies.getCorePoliciesNone(tokengamePlayBand));
		
			tokengamePlayBand.addCommandButton(getSkipBackwardButton(), RibbonElementPriority.TOP);
			tokengamePlayBand.addCommandButton(getSeekBackwardButton(), RibbonElementPriority.TOP);
			tokengamePlayBand.addCommandButton(getJumpIntoSubProcessButton(), RibbonElementPriority.TOP);
			tokengamePlayBand.addCommandButton(getPauseButton(), RibbonElementPriority.TOP);
			tokengamePlayBand.addCommandButton(getPlayButton(), RibbonElementPriority.TOP);
			tokengamePlayBand.addCommandButton(getStopButton(), RibbonElementPriority.TOP);
			tokengamePlayBand.addCommandButton(getJumpOutOfSubprocessButton(), RibbonElementPriority.TOP);
			tokengamePlayBand.addCommandButton(getSeekForwardButton(), RibbonElementPriority.TOP);
			tokengamePlayBand.addCommandButton(getSkipForwardButton(), RibbonElementPriority.TOP);
		}
		
		return tokengamePlayBand;
	}

	private JRibbonBand getTokengameEditBand (){
		
		if (tokengameEditBand == null) {	
			tokengameEditBand = new JRibbonBand(Messages.getString("tgEditBand.textBandTitle"),new tokengame_edit_step_by_step());
			tokengameEditBand.setResizePolicies(CoreRibbonResizePolicies.getCorePoliciesNone(tokengameEditBand));
		
			tokengameEditBand.addCommandButton(getStepByStepButton(), RibbonElementPriority.TOP);
			tokengameEditBand.addCommandButton(getAutoPlayButton(), RibbonElementPriority.TOP);
			tokengameEditBand.addCommandButton(getTokengameCloseButton(), RibbonElementPriority.TOP);
		}	
		
		return tokengameEditBand;
	}	
	
	/***********/
	/* BUTTONS */
	/***********/
	private JCommandButton getNewButton() {
		
		if (newButton == null) {
			newButton = new JCommandButton(Messages.getString("Document.new.text"), new file_new());
			newListener = new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_NEW, AbstractViewEvent.NEW, newButton);
			newButton.addActionListener(newListener);			
			setTooltip(newButton, "Document.new");
		}
		
		return newButton;
	}

	private JCommandButton getOpenButton() {
		
		if (openButton == null) {
			openButton = new JCommandButton(Messages.getString("Document.open.text"), new file_open());
			openButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_OPEN, AbstractViewEvent.OPEN, openButton));			
			setTooltip(openButton, "Document.open");
		}
		
		return openButton;
	}

	private JCommandButton getRecentButton() {
		
		if (recentButton == null) {
			recentButton = new JCommandButton(Messages.getString("Document.recent.text"), new file_recent());
			recentButton.setCommandButtonKind(JCommandButton.CommandButtonKind.POPUP_ONLY);
			
			recentButton.setPopupCallback(new PopupPanelCallback() {
				@Override
				public JPopupPanel getPopupPanel(JCommandButton commandButton) {
				    return getRecentMenu();
				}
			});
			setPopupTooltip(recentButton, "Document.recent");
		}
		
		return recentButton;
	}

	private JCommandPopupMenu getRecentMenu() {
		
		m_recentMenu = new JCommandPopupMenu();
		Vector<?> v = ConfigurationManager.getConfiguration().getRecentFiles();
		if (v.size() != 0) {
			for (int idx = 0; idx < v.size(); idx++) {
				String name = ((WoPeDRecentFile) v.get(idx)).getName();
				String path = ((WoPeDRecentFile) v.get(idx)).getPath();

				JCommandMenuButton recentMenuItem = new JCommandMenuButton(
						name, new file_recent());
				recentMenuItem.addActionListener(new recentFileListener(path));

				m_recentMenu.addMenuButton(recentMenuItem);
			}
		} else {
			JCommandMenuButton emptyItem = new JCommandMenuButton(
					Messages.getString("Menu.File.RecentMenu.empty"),
					new file_recent());
			m_recentMenu.addMenuButton(emptyItem);
			emptyItem.setEnabled(false);
			m_recentMenu.addMenuButton(emptyItem);
		}
		
		return m_recentMenu;
	}

	private JCommandButton getCloseButton() {
		
		if (closeButton == null) {
			closeButton = new JCommandButton(Messages.getString("Document.close.text"), new file_close());
			closeButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_CLOSE, AbstractViewEvent.CLOSE, closeButton));			
			setTooltip(closeButton, "Document.close");
		}
		
		return closeButton;
	}
	
	private JCommandButton getSaveButton() {
		
		if (saveButton == null) {
			saveButton = new JCommandButton(Messages.getString("Save.save.text"), new file_save());
			saveButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_SAVE, AbstractViewEvent.SAVE, saveButton));			
			setTooltip(saveButton, "Save.save");
		}
		
		return saveButton;
	}
	
	private JCommandButton getSaveAsButton() {
		
		if (saveAsButton == null) {
			saveAsButton = new JCommandButton(Messages.getString("Save.saveAs.text"), new file_saveas());
			saveAsButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_SAVEAS, AbstractViewEvent.SAVEAS, saveAsButton));			
			setTooltip(saveAsButton, "Save.saveAs");
		}
		
		return saveAsButton;
	}
	
	private JCommandButton getPrintButton() {
		
		if (printButton == null) {
			printButton = new JCommandButton(Messages.getString("DataOutput.print.text"), new file_print());
			printButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_PRINT, AbstractViewEvent.PRINT, printButton));			
			setTooltip(printButton, "DataOutput.print");
		}
		
		return printButton;
	}
		
	private JCommandButton getExportButton() {
		
		if (exportAsButton == null) {
			exportAsButton = new JCommandButton(Messages.getString("DataOutput.exportAs.text"), new file_exportas());
			exportAsButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_EXPORT, AbstractViewEvent.EXPORT, exportAsButton));			
			setTooltip(exportAsButton, "DataOutput.exportAs");
		}
		
		return exportAsButton;
	}
		
	private JCommandButton getImportApromoreButton() {
		
		if (importApromoreButton == null) {		
			importApromoreButton = new JCommandButton(Messages.getString("Apromore.aproImport.text"), new apromore_import());
			importApromoreButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_IMPORTAPRO, AbstractViewEvent.IMPORTAPRO, importApromoreButton));
			setTooltip(importApromoreButton, "Apromore.aproImport");
		}
		
		return importApromoreButton;
	}
				
	private JCommandButton getExportApromoreButton() {
		
		if (exportApromoreButton == null) {		
			exportApromoreButton = new JCommandButton(Messages.getString("Apromore.aproExport.text"), new apromore_export());
			exportApromoreButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_EXPORTAPRO, AbstractViewEvent.EXPORTAPRO, exportApromoreButton));
			setTooltip(exportApromoreButton, "Apromore.aproExport");
		}
		
		return exportApromoreButton;
	}
				

	private JCommandButton getUndoButton() {
		
		if (undoButton == null) {
			undoButton = new JCommandButton(Messages.getString("Edit.undo.text"), new editor_undo());
			undoButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_UNDO, AbstractViewEvent.UNDO, undoButton));			
			setTooltip(undoButton, "Edit.undo");
		}
		
		return undoButton;
	}

	private JCommandButton getRedoButton() {
		
		if (redoButton == null) {
			redoButton = new JCommandButton(Messages.getString("Edit.redo.text"), new editor_redo());
			redoButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_REDO, AbstractViewEvent.REDO, redoButton));			
			setTooltip(redoButton, "Edit.redo");
		}
		
		return redoButton;
	}

	private JCommandButton getCutButton() {
		
		if (cutButton == null) {
			cutButton = new JCommandButton(Messages.getString("Edit.cut.text"), new editor_cut());
			cutButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_CUT, AbstractViewEvent.CUT, cutButton));			
			setTooltip(cutButton, "Edit.cut");
		}
		
		return cutButton;
	}

	private JCommandButton getCopyButton() {
		
		if (copyButton == null) {
			copyButton = new JCommandButton(Messages.getString("Edit.copy.text"), new editor_copy());
			copyButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_COPY, AbstractViewEvent.COPY, copyButton));			
			setTooltip(copyButton, "Edit.copy");
		}
		
		return copyButton;
	}

	private JCommandButton getPasteButton() {
		
		if (pasteButton == null) {
			pasteButton = new JCommandButton(Messages.getString("Edit.paste.text"), new editor_paste());
			pasteButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_PASTE, AbstractViewEvent.PASTE, pasteButton));			
			setTooltip(pasteButton, "Edit.paste");
		}
		
		return pasteButton;
	}

	private JCommandButton getGroupButton() {
		
		if (groupButton == null) {
			groupButton = new JCommandButton(Messages.getString("Edit.group.text"), new editor_group());
			groupButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_GROUP, AbstractViewEvent.GROUP, groupButton));			
			setTooltip(groupButton, "Edit.group");
		}
		
		return groupButton;
	}

	private JCommandButton getUngroupButton() {
		
		if (ungroupButton == null) {
			ungroupButton = new JCommandButton(Messages.getString("Edit.ungroup.text"), new editor_ungroup());
			ungroupButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_UNGROUP, AbstractViewEvent.UNGROUP, ungroupButton));			
			setTooltip(ungroupButton, "Edit.ungroup");
		}
		
		return ungroupButton;
	}

	private JCommandButton getPlaceButton() {
		
		if (placeButton == null) {
			placeButton = new JCommandButton(Messages.getString("Forms.place.text"), new forms_place());
			placeButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_DRAWMODE_PLACE, AbstractViewEvent.DRAWMODE_PLACE, placeButton));
			setTooltip(placeButton, "Forms.place");
		}
		
		return placeButton;
	}

	private JCommandButton getTransitionButton() {
		
		if (transitionButton == null) {
			 transitionButton = new JCommandButton(Messages.getString("Forms.transition.text"), new forms_transition());
			transitionButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_DRAWMODE_TRANSITION, AbstractViewEvent.DRAWMODE_TRANSITION, transitionButton));
			setTooltip(transitionButton, "Forms.transition");
		}
		
		return transitionButton;
	}

	private JCommandButton getXorSplitButton() {
		
		if (xorSplitButton == null) {
			xorSplitButton = new JCommandButton(Messages.getString("Forms.XORSplit.text"), new forms_xor_split());
			xorSplitButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_DRAWMODE_XORSPLIT, AbstractViewEvent.DRAWMODE_XORSPLIT, xorSplitButton));
			setTooltip(xorSplitButton, "Forms.XORSplit");
		}
		
		return xorSplitButton;
	}

	private JCommandButton getXorJoinButton() {
		
		if (xorJoinButton == null) {
			xorJoinButton = new JCommandButton(Messages.getString("Forms.XORJoin.text"), new forms_xor_join());
			xorJoinButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_DRAWMODE_XORJOIN, AbstractViewEvent.DRAWMODE_XORJOIN, xorJoinButton));
			setTooltip(xorJoinButton, "Forms.XORJoin");
		}
		
		return xorJoinButton;
	}

	
	private JCommandButton getAndSplitButton() {
		if (andSplitButton == null) {
			andSplitButton = new JCommandButton(Messages.getString("Forms.ANDSplit.text"),new forms_and_split());
			andSplitButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_DRAWMODE_ANDSPLIT, AbstractViewEvent.DRAWMODE_ANDSPLIT, andSplitButton));
			setTooltip(andSplitButton, "Forms.ANDSplit");
		}
		
		return andSplitButton;
	}

	private JCommandButton getAndJoinButton() {
		
		if (andJoinButton == null) {
			andJoinButton = new JCommandButton(Messages.getString("Forms.ANDJoin.text"), new forms_and_join());
			andJoinButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_DRAWMODE_ANDJOIN, AbstractViewEvent.DRAWMODE_ANDJOIN, andJoinButton));
			setTooltip(andJoinButton, "Forms.ANDJoin");
		}
		
		return andJoinButton;
	}

	private JCommandButton getXorSplitJoinButton() {
		if (xorSplitJoinButton == null) {
			xorSplitJoinButton = new JCommandButton(Messages.getString("Forms.XORSplitJoin.text"), new forms_xor_split_join());
			xorSplitJoinButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_DRAWMODE_XORSPLITJOIN, AbstractViewEvent.DRAWMODE_XORSPLITJOIN, xorSplitJoinButton));
			setTooltip(xorSplitJoinButton, "Forms.XORSplitJoin");
		}
		
		return xorSplitJoinButton;
	}

	private JCommandButton getAndSplitJoinButton() {
		
		if (andSplitJoinButton == null) {
			andSplitJoinButton = new JCommandButton(Messages.getString("Forms.ANDSplitJoin.text"), new forms_and_split_join());
			andSplitJoinButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_DRAWMODE_ANDSPLITJOIN, AbstractViewEvent.DRAWMODE_ANDSPLITJOIN, andSplitJoinButton));
			setTooltip(andSplitJoinButton, "Forms.ANDSplitJoin");
		}
		
		return andSplitJoinButton;
	}

	private JCommandButton getAndJoinXorSplitButton() {
		
		if (andJoinXorSplitButton == null) {
			andJoinXorSplitButton = new JCommandButton(Messages.getString("Forms.ANDJoinXORSplit.text"), new forms_and_join_xor_split());
			andJoinXorSplitButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_DRAWMODE_ANDJOINXORSPLIT, AbstractViewEvent.DRAWMODE_ANDJOIN_XORSPLIT, andJoinXorSplitButton));
			setTooltip(andJoinXorSplitButton, "Forms.ANDJoinXORSplit");
		}
		
		return andJoinXorSplitButton;
	}

	private JCommandButton getXorJoinAndSplitButton() {
		
		if (xorJoinAndSplitButton == null) {
			xorJoinAndSplitButton = new JCommandButton(Messages.getString("Forms.XORJoinANDSplit.text"), new forms_xor_join_and_split());
			xorJoinAndSplitButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_DRAWMODE_XORJOINANDSPLIT, AbstractViewEvent.DRAWMODE_XORJOIN_ANDSPLIT, xorJoinAndSplitButton));
			setTooltip(xorJoinAndSplitButton, "Forms.XORJoinANDSplit");
		}
		
		return xorJoinAndSplitButton;
	}

	private JCommandButton getSubprocessButton() {
		
		if (subprocessButton == null) {
			subprocessButton = new JCommandButton(Messages.getString("Forms.subprocess.text"), new forms_subprocess());
			subprocessButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_DRAWMODE_SUB, AbstractViewEvent.DRAWMODE_SUB, subprocessButton));
			setTooltip(subprocessButton, "Forms.subprocess");
		}
		
		return subprocessButton;
	}
		
	public JCommandButton getCoverabilityGraphButton() {
		
		if (coverabilityGraphButton == null) {
			coverabilityGraphButton = new JCommandMenuButton(Messages.getString("Tools.reachabilityGraph.text"), new analyze_reachability_graph());
			coverabilityGraphButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_REACHGRAPH_START, 																			 AbstractViewEvent.REACHGRAPH, coverabilityGraphButton));
			setTooltip(coverabilityGraphButton, "Tools.reachabilityGraph");
		}
		
		return coverabilityGraphButton;
	}

	private JCommandButton getColoringButton() {
		
		if (coloringButton == null) {
			coloringButton = new JCommandButton(Messages.getString("Tools.coloring.text"), new analyze_coloring());
			coloringButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_COLORING, AbstractViewEvent.COLORING, coloringButton));
			setTooltip(coloringButton, "Tools.coloring");
		}
		
		return coloringButton;
	}

	private JCommandButton getSemanticalAnalysisButton() {
		
		if (semanticalAnalysisButton == null) {
			semanticalAnalysisButton = new JCommandButton(Messages.getString("Tools.semanticalAnalysis.text"), new analyze_semanticalanalysis());
			semanticalAnalysisButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_WOPED, AbstractViewEvent.ANALYSIS_WOPED, semanticalAnalysisButton));
			setTooltip(semanticalAnalysisButton, "Tools.semanticalAnalysis");
		}
		
		return semanticalAnalysisButton;
	}

	private JCommandButton getCapacityPlanningButton() {
		
		if (capacityPlanningButton == null) {
			capacityPlanningButton = new JCommandButton(Messages.getString("Tools.capacityPlanning.text"), new analyze_capacityplanning());
			capacityPlanningButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_QUANTCAP, AbstractViewEvent.QUANTCAP, capacityPlanningButton));
			setTooltip(capacityPlanningButton, "Tools.capacityPlanning");
		}
		
		return capacityPlanningButton;
	}

	private JCommandButton getQuantitativeSimulationButton() {
		
		if (quantitativeSimulationButton == null) {
			quantitativeSimulationButton = new JCommandButton(Messages.getString("Tools.quantitativeSimulation.text"),new analyze_quantitative_simulation());
			quantitativeSimulationButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_QUANTSIM, AbstractViewEvent.QUANTSIM, quantitativeSimulationButton));
			setTooltip(quantitativeSimulationButton, "Tools.quantitativeSimulation");
		}
		
		return quantitativeSimulationButton;
	}

	private JCommandButton getProcessMetricsButton() {
		
		if (processMetricsButton == null) {
			processMetricsButton = new JCommandButton(Messages.getString("Metrics.processmetrics.text"), new analyze_metric());
			processMetricsButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_METRIC, AbstractViewEvent.ANALYSIS_METRIC, processMetricsButton));
			setTooltip(processMetricsButton, "Metrics.processmetrics");
		}
		
		return processMetricsButton;
	}

	private JCommandButton getProcessMassAnalyzeButton() {
		
		if (processMassAnalyzeButton == null) {
			processMassAnalyzeButton = new JCommandButton(Messages.getString("Metrics.processmetricsmassanalysis.text"), new analyze_metric_mass_import());
			processMassAnalyzeButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_MASSMETRICANALYSE, AbstractViewEvent.ANALYSIS_MASSMETRICANALYSE, processMassAnalyzeButton));
			setTooltip(processMassAnalyzeButton, "Metrics.processmetricsmassanalysis");
		}
		
		return processMassAnalyzeButton;
	}

	private JCommandButton getProcessMetricsBuilderButton() {
		
		if (processMetricsBuilderButton == null) {
			processMetricsBuilderButton = new JCommandButton(Messages.getString("Metrics.processmetricsbuilder.text"), new analyze_metric_builder());
			processMetricsBuilderButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_METRICSBUILDER, AbstractViewEvent.ANALYSIS_METRICSBUILDER, processMetricsBuilderButton));
			setTooltip(processMetricsBuilderButton, "Metrics.processmetricsbuilder");
		}
		
		return processMetricsBuilderButton;
	}

	private JCommandButton getChangeOrientationButton() {
		
		if (changeOrientationButton == null) {
			changeOrientationButton = new JCommandButton(Messages.getString("View.changeModellingDirection.text"),new view_change_modelling_direction());
			changeOrientationButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_ROTATEVIEW, AbstractViewEvent.ROTATEVIEW, changeOrientationButton));
			setTooltip(changeOrientationButton, "View.changeModellingDirection");
		}
		
		return changeOrientationButton;
	}

	private JCommandButton getOptimizeLayoutButton() {
		
		if (optimizeLayoutButton == null) {
			optimizeLayoutButton = new JCommandButton(Messages.getString("View.optimizeLayout.text"),new view_optimize_layout());
			optimizeLayoutButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_GRAPHBEAUTIFIER_DEFAULT, AbstractViewEvent.GRAPHBEAUTIFIER, optimizeLayoutButton));
			setTooltip(optimizeLayoutButton, "View.optimizeLayout");
		}
		
		return optimizeLayoutButton;
	}

	private JCommandButton getArrangeButton() {
		
		if (arrangeButton == null) {
			arrangeButton = new JCommandButton(Messages.getString("WindowPreferences.arrange.text"), new window_arrange());
			arrangeButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_ARRANGE, AbstractViewEvent.ARRANGE, arrangeButton));
			setTooltip(arrangeButton, "WindowPreferences.arrange");
		}
		
		return arrangeButton;
	}

	private JCommandButton getCascadeButton() {
		
		if (cascadeButton == null) {
			cascadeButton = new JCommandButton(Messages.getString("WindowPreferences.cascade.text"), new window_cascadewindows());
			cascadeButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_CASCADE, AbstractViewEvent.CASCADE, cascadeButton));
			setTooltip(cascadeButton, "WindowPreferences.arrange");
		}
		
		return cascadeButton;
	}
	
	private JRibbonComponent getOverviewComponent() {
		
		if (overviewComponent == null) {
			overviewCheckbox = new JCheckBox(Messages.getString("Sidebar.Overview.Title"));
			overviewComponent = new JRibbonComponent(overviewCheckbox);
			overviewCheckbox.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_SHOWOVERVIEW, AbstractViewEvent.VIEWEVENTTYPE_GUI, overviewCheckbox));
			overviewCheckbox.setToolTipText(Messages.getString("Sidebar.Overview.Tooltip"));
		}
		
		return overviewComponent;
	}

	private JRibbonComponent getTreeviewComponent() {
		
		if (treeviewComponent == null) {
			treeviewCheckbox = new JCheckBox(Messages.getString("Sidebar.Treeview.Title"));
			treeviewComponent = new JRibbonComponent(treeviewCheckbox);
			treeviewCheckbox.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_SHOWTREEVIEW, AbstractViewEvent.VIEWEVENTTYPE_GUI, treeviewCheckbox));
			treeviewCheckbox.setToolTipText(Messages.getString("Sidebar.Treeview.Tooltip"));
		}
		
		return treeviewComponent;
	}

	private JCommandButton getConfigurationButton() {
		
		if (configurationButton == null) {
			configurationButton = new JCommandButton(Messages.getString("OptionsAndHelp.Configuration.text"), new help_configuration());
			configurationButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_SHOWCONFIG, AbstractViewEvent.CONFIG, configurationButton));		
			setTooltip(configurationButton, "OptionsAndHelp.Configuration");
		}
		
		return configurationButton;
	}

	private JCommandButton getManualButton() {
		
		if (manualButton == null) {
			manualButton = new JCommandButton(Messages.getString("OptionsAndHelp.Manual.text"), new help_manual());
			manualButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_SHOWHELPINDEX, AbstractViewEvent.HELP, manualButton));
			setTooltip(manualButton, "OptionsAndHelp.Manual");
		}
		
		return manualButton;
	}

	private JCommandButton getContentsButton() {
		
		if (contentsButton == null) {
			contentsButton = new JCommandButton(Messages.getString("OptionsAndHelp.Contents.text"),new help_contents());
			contentsButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_SHOWHELPCONTENTS, AbstractViewEvent.HELP_CONTENTS, contentsButton));
			setTooltip(contentsButton, "OptionsAndHelp.Contents");
		}
		
		return contentsButton;
	}

	private JCommandButton getSampleNetsButton() {
		
		if (sampleNetsButton == null) {
			sampleNetsButton = new JCommandButton(Messages.getString("OptionsAndHelp.SampleNets.text"),new help_smaplenets());
			sampleNetsButton.setCommandButtonKind(JCommandButton.CommandButtonKind.POPUP_ONLY);
			sampleNetsButton.setPopupCallback(new PopupPanelCallback() {
				@Override
				public JPopupPanel getPopupPanel(JCommandButton commandButton) {
			        return getSampleMenu();				
				}
			});	
			setPopupTooltip(sampleNetsButton, "OptionsAndHelp.SampleNets");
		}
		
		return sampleNetsButton;
	}

	private JCommandButton getReportBugButton() {
		
		if (reportBugButton == null) {
			reportBugButton = new JCommandButton(Messages.getString("OptionsAndHelp.ReportBug.text"), new help_reportbug());
			reportBugButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_SHOWBUGREPORT, AbstractViewEvent.BUGREPORT, reportBugButton));
			setTooltip(reportBugButton, "OptionsAndHelp.ReportBug");
		}
		
		return reportBugButton;
	}

	private JCommandButton getAboutButton() {
		
		if (aboutButton == null) {
			aboutButton = new JCommandButton(Messages.getString("OptionsAndHelp.About.text"), new help_about());
			aboutButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_SHOWABOUT, AbstractViewEvent.ABOUT, aboutButton));
			setTooltip(aboutButton, "OptionsAndHelp.About");
		}
		
		return aboutButton;
	}

	private JCommandButton getTokengameStartButton() {
		
		if (tokengameStartButton == null) {
			tokengameStartButton = new JCommandButton(Messages.getString("Tools.tokengame.text"), new analyze_tokengame());
			tokengameStartListener = new ActionButtonListener(m_mediator, ActionFactory.ACTIONID_OPEN_TOKENGAME, AbstractViewEvent.OPEN_TOKENGAME, getTokengameStartButton());
			tokengameStartButton.addActionListener(tokengameStartListener);
			setTooltip(tokengameStartButton, "Tools.tokengame");
		}
		
		return tokengameStartButton;
	}

	private JCommandPopupMenu getSampleMenu() {
		
		if (m_sampleMenu == null) {
			m_sampleMenu = new JCommandPopupMenu();
			try {
				String innerPath = "org/woped/file/samples/";
				String path = this.getClass().getResource("/" + innerPath)
						.toExternalForm();
				// Jar file access
				if (path.indexOf("jar:file:") != -1) {
					String fn = path.replaceAll("jar:file:", "");
					// find jar start
					int n = fn.indexOf("!");
					if (n == -1)
						n = fn.length();
					// read Jar File Name
					fn = fn.substring(0, n);
					// Replace all whitespaces in filename
					fn = fn.replaceAll("%20", " ");
					JarFile jf = new JarFile(fn);
					Enumeration<JarEntry> e = jf.entries();
					ZipEntry ze;
					// process entries
					while (e.hasMoreElements()) {
						ze = (ZipEntry) e.nextElement();
						String name;
						String samplepath;
						if (ze.getName().indexOf(innerPath) == 0
								&& ze.getName().length() > innerPath.length()) {
							samplepath = "/" + ze.getName();
							name = ze.getName().substring(
									ze.getName().lastIndexOf("/") + 1);
							JCommandMenuButton sampleItem = new JCommandMenuButton(
									name, new help_smaplenets());
							sampleItem
									.addActionListener(new sampleFileListener(
											samplepath));

							m_sampleMenu.addMenuButton(sampleItem);
						}
					}
				}
				// Normal dir access
				else {
					path = "../WoPeD-FileInterface/bin/" + innerPath;
					File sampleDir = new File(path);
					if (sampleDir.isDirectory()) {
						for (int idx = 0; idx < sampleDir.listFiles().length; idx++) {
							if (!sampleDir.listFiles()[idx].isDirectory()) {
								JCommandMenuButton sampleItem = new JCommandMenuButton(
										sampleDir.listFiles()[idx].getName(),
										new help_smaplenets());
								String name = sampleDir.listFiles()[idx]
										.getAbsolutePath();
								sampleItem
										.addActionListener(new sampleFileListener(
												name));

								m_sampleMenu.addMenuButton(sampleItem);
							}
						}
					} else {
						LoggerManager.error(Constants.GUI_LOGGER,
								"No sample nets found in directory " + path);
					}
				}
			} catch (Exception ex) {
				LoggerManager.error(Constants.GUI_LOGGER,
						"Cannot find sample files");
				ex.printStackTrace();
			}
		}
		return m_sampleMenu;
	}

	private JCommandButton getSkipBackwardButton() {
		
		if (skipBackwardButton == null) {
			skipBackwardButton = new JCommandButton(Messages.getString("tgPlayBand.skipBackwardButton.text"), new tokengame_play_skip_backward());
//			skipBackwardButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.???, AbstractViewEvent.???, skipBackwardButton));			
			setTooltip(skipBackwardButton, "tgPlayBand.skipBackwardButton");
		}
		
		return skipBackwardButton;
	}

	private JCommandButton getSeekBackwardButton() {
		
		if (seekBackwardButton == null) {
			seekBackwardButton = new JCommandButton(Messages.getString("tgPlayBand.seekBackwardButton.text"), new tokengame_play_seek_backward());
//			seekBackwardButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_???, AbstractViewEvent.???, seekBackwardButton));			
			setTooltip(seekBackwardButton, "tgPlayBand.seekBackwardButton");
		}
		
		return seekBackwardButton;
	}

	private JCommandButton getJumpIntoSubProcessButton() {
		
		if (jumpIntoSubProcessButton == null) {
			jumpIntoSubProcessButton = new JCommandButton(Messages.getString("tgPlayBand.jumpIntoSubProcessButton.text"), new tokengame_play_jump_into_subprocess());
//			jumpIntoSubProcessButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_???, AbstractViewEvent.???, jumpIntoSubProcessButton));			
			setTooltip(jumpIntoSubProcessButton, "tgPlayBand.jumpIntoSubProcessButton");
		}
		
		return jumpIntoSubProcessButton;
	}

	private JCommandButton getPauseButton() {
		
		if (pauseButton == null) {
			pauseButton = new JCommandButton(Messages.getString("tgPlayBand.pauseButton.text"), new tokengame_play_pause());
//			pauseButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_???, AbstractViewEvent.???, pauseButton));			
			setTooltip(pauseButton, "tgPlayBand.pauseButton");
		}
		
		return pauseButton;
	}

	private JCommandButton getPlayButton() {
		
		if (playButton == null) {
			playButton = new JCommandButton(Messages.getString("tgPlayBand.playButton.text"), new tokengame_play_start());
//			playButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_???, AbstractViewEvent.???, playButton));			
			setTooltip(playButton, "tgPlayBand.playButton");
		}
		
		return playButton;
	}

	private JCommandButton getStopButton() {
		
		if (stopButton == null) {
			stopButton = new JCommandButton(Messages.getString("tgPlayBand.stopButton.text"), new tokengame_play_stop());
//			stopButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_???, AbstractViewEvent.???, stopButton));			
			setTooltip(stopButton, "tgPlayBand.stopButton");
		}
		
		return stopButton;
	}

	private JCommandButton getJumpOutOfSubprocessButton() {
		
		if (jumpOutOfSubprocessButton == null) {
			jumpOutOfSubprocessButton = new JCommandButton(Messages.getString("tgPlayBand.jumpOutOfSubprocessButton.text"), new tokengame_play_jump_out_of_subprocess());
//			jumpOutOfSubprocessButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_???, AbstractViewEvent.???, jumpOutOfSubprocessButton));			
			setTooltip(jumpOutOfSubprocessButton, "tgPlayBand.jumpOutOfSubprocessButton");
		}
		
		return jumpOutOfSubprocessButton;
	}

	private JCommandButton getSeekForwardButton() {
		
		if (seekForwardButton == null) {
			seekForwardButton = new JCommandButton(Messages.getString("tgPlayBand.seekForwardButton.text"), new tokengame_play_seek_forward());
//			seekForwardButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_???, AbstractViewEvent.???, seekForwardButton));			
			setTooltip(seekForwardButton, "tgPlayBand.seekForwardButton");
		}
		
		return seekForwardButton;
	}

	private JCommandButton getSkipForwardButton() {
		
		if (skipForwardButton == null) {
			skipForwardButton = new JCommandButton(Messages.getString("tgPlayBand.skipForwardButton.text"), new tokengame_play_skip_forward());
//			skipForwardButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_???, AbstractViewEvent.???, skipForwardButton));			
			setTooltip(skipForwardButton, "tgPlayBand.skipForwardButton");
		}
		
		return skipForwardButton;
	}
	
	private JCommandButton getStepByStepButton() {
		
		if (stepByStepButton == null) {
			stepByStepButton = new JCommandButton(Messages.getString("tgEditBand.stepByStepButton.text"), new tokengame_edit_step_by_step());
//			stepByStepButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_???, AbstractViewEvent.???, stepByStepButton));			
			setTooltip(stepByStepButton, "tgEditBand.stepByStepButton");
		}
		
		return stepByStepButton;
	}
		
	private JCommandButton getAutoPlayButton() {
		
		if (autoPlayButton == null) {
			autoPlayButton = new JCommandButton(Messages.getString("tgEditBand.autoPlayButton.text"), new tokengame_edit_autoPlay());
//			autoPlayButton.addActionListener(new ActionButtonListener(m_mediator,ActionFactory.ACTIONID_???, AbstractViewEvent.???, sautoPlayButton));			
			setTooltip(autoPlayButton, "tgEditBand.autoPlayButton");
		}
		
		return autoPlayButton;
	}

	private JCommandButton getTokengameCloseButton() {
		
		if (tokengameCloseButton == null) {
			tokengameCloseButton = new JCommandButton(Messages.getString("tokengameTGBand.tgExitBand.text"), new tokengame_tokengame_exit());
			tokengameCloseListener = new ActionButtonListener(m_mediator, ActionFactory.ACTIONID_CLOSE_TOKENGAME, AbstractViewEvent.CLOSE_TOKENGAME, tokengameCloseButton);
			tokengameCloseButton.addActionListener(tokengameCloseListener);
			setTooltip(tokengameCloseButton, "tokengameTGBand.tgExitBand");
		}
		
		return tokengameCloseButton;
	}
		
	public void fireViewEvent(AbstractViewEvent viewevent) {
		this.m_mediator.fireViewEvent(viewevent);
	}

	@Override
	public void arrangeFrames() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cascadeFrames() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IEditor> getAllEditors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Component getComponent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEditor getEditorFocus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Component getPropertyChangeSupportBean() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IReachabilityGraph getReachGraphFocus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void hideEditor(IEditor editor) {
		// TODO Auto-generated method stub
		
	}

	public void setMediator(AbstractApplicationMediator mediator) {
		m_mediator = mediator;		
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refreshFocusOnFrames() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeToolBar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFirstTransitionActive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSimulatorBar(Object simulatorBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void switchToolBar(boolean change) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEditor(IEditor editor) {
	    getRibbon().setSelectedTask(getEditTask());
	}

	@Override
	public void removeEditor(IEditor editor) {
    	if (getAllEditors().size() == 0) {
    		getRibbon().setSelectedTask(getFileTask());
    	}		
	}

	@Override
	public void renameEditor(IEditor editor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectEditor(IEditor editor) {
	    if (editor.isTokenGameEnabled()) {
	    	getRibbon().setVisible(getTokengameGroup(), true);
	    	getRibbon().setSelectedTask(getTokengameTask());
		}
		else {
			if (getRibbon().isVisible(getTokengameGroup())) {
				getRibbon().setVisible(getTokengameGroup(), false);
				getRibbon().setSelectedTask(getAnalyzeTask());
			}
	    }
	}

	@Override
	public boolean isMaximized() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateRecentMenu() {
		// TODO Auto-generated method stub
		
	}
}