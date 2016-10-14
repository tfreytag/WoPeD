package org.woped;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.woped.editor.AllTestsEditor;
import org.woped.gui.AllTestsGui;
import org.woped.tests.soundness.SoundnessTests;
import org.woped.core.AllTestsCore;
import org.woped.file.AllTestsFile;
import org.woped.metrics.AllTestsMetrics;
import org.woped.qualanalysis.AllTestsQualAnalysis;


@RunWith(Suite.class)
@Suite.SuiteClasses({SoundnessTests.class,
        AllTestsCore.class,
        AllTestsEditor.class,
        AllTestsFile.class,
        AllTestsGui.class,
        AllTestsMetrics.class,
        AllTestsQualAnalysis.class})

public class AllTests {
}