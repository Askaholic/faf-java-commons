package com.faforever.commons.fa;

import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ForgedAllianceExePatcherTest {

  @Rule
  public TemporaryFolder fafBinDirectory = new TemporaryFolder();

  @Test
  public void patchVersion() throws Exception {
    Path dummyExe = createDummyExeFile();
    ForgedAllianceExePatcher.patchVersion(dummyExe, 3660);

    assertThat(Files.hash(dummyExe.toFile(), Hashing.md5()).toString(), is("4de5eed29b45b640fe64aa22808631c3"));
  }

  private Path createDummyExeFile() throws IOException {
    Path dummyExe = fafBinDirectory.getRoot().toPath().resolve("ForgedAlliance.exe");
    createFileWithSize(dummyExe, 12_444_928);
    return dummyExe;
  }

  @Test
  public void readVersion() throws Exception {
    Path dummyExe = createDummyExeFile();
    ForgedAllianceExePatcher.patchVersion(dummyExe, 3660);

    int result = ForgedAllianceExePatcher.readVersion(dummyExe);
    assertThat(result, is(3660));
  }

  private void createFileWithSize(Path file, int size) throws IOException {
    try (RandomAccessFile randomAccessFile = new RandomAccessFile(file.toFile(), "rw")) {
      randomAccessFile.setLength(size);
    }
  }
}
