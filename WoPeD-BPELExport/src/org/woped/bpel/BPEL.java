package org.woped.bpel;

import java.io.File;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.woped.core.model.PetriNetModelProcessor;
import org.woped.core.utilities.Utils;
import org.woped.editor.utilities.FileFilterImpl;


//TODO class description
public class BPEL {

	private static BPEL bpelMainClass;

	private Vector<String> extensions;

	private FileFilter filter;


	//TODO method description
	public BPEL() {
		this.initFilefilter();
		bpelMainClass = this;
	}

	//TODO method description
	private final void initFilefilter() {
		this.extensions = new Vector<String>();
		this.extensions.add("bpel");
		this.filter = new FileFilterImpl(FileFilterImpl.BPELFilter,
				"BPEL (*.bpel)", this.extensions);
	}

	//TODO method description
	public static final BPEL getBPELMainClass() {
		if (bpelMainClass == null) {
			new BPEL();
		}
		return bpelMainClass;
	}

	//TODO method description
	public FileFilter getFilefilter() {
		return this.filter;
	}

	//TODO method description
	public boolean checkFileExtension(JFileChooser jfc)
	{
		return ((FileFilterImpl) jfc.getFileFilter()).getFilterType() == FileFilterImpl.BPELFilter;
	}

	//TODO method description
	public String getSavePath(String basicPath,JFileChooser jfc)
	{
		return basicPath + Utils.getQualifiedFileName(jfc.getSelectedFile().getName(), this.extensions);
	}

	//TODO method description
	public boolean saveFile(String Path, PetriNetModelProcessor pnp)
	{
		new File(Path);
		return true;
	}

}