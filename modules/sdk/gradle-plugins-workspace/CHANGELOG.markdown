# Liferay Gradle Plugins Workspace Change Log

## 11.0.2 - 2024-08-21

### Commits
- [LPD-30302] gradle-plugins-workspace: fixes test failure - pin to published
version of ServiceBuilder instead of looking for latest (8ee2e32f8d)

### Dependencies
- [LPD-27018] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.147.

## 11.0.1 - 2024-08-19

### Commits
- [LPD-30302] gradle-plugins-workspace: test: fixes classpath issue (fc6ff26875)
- [LPD-30302] gradle-plugins-workspace: updateFileVersions (0bb43154a4)
- [LPD-30302] gradle-plugins-workspace: adds opens when at or above Java 11
(a9fb38fcff)
- [LPD-31541] gradle-plugins-workspace: excludes org.osgi.core-5.0.0.jar from
targetPlatform tests (747e065a29)

## 11.0.0 - 2024-08-15

### Commits
- [LPD-24834] Wordsmith/SF (a2dc5ecdc8)
- [LPD-24834] Sublime sort (0cbf781580)
- [LPD-24834] gradle-plugins-workspace: updateFileVersions (f84263a460)
- [LPD-33601] gradle-plugins-workspace: renames test variable to indicate that
ordering matters (712ea41466)
- [LPD-33601] gradle-plugins-workspace: checks the CDN first before Maven
Central (f9b07a2bc3)
- [LPD-33601] gradle-plugins-workspace: adds test case for default repository
ordering (a8e06e0d54)
- [LPD-31541] - test classpath (add38d1259)
- [LPD-24834] gradle-plugins-workspace: SF: sorts variables in test files that
check file contents (58f2a7c3e8)
- [LPD-24834] gradle-plugins-workspace: SF: sorts string array values in test
files that check file contents (2db5a6bcd1)
- [LPD-24834] gradle-plugins-workspace: uses StringUtil.concat and adjusts
wording (538625f3cd)
- [LPD-24834] gradle-plugins-workspace: test: SF according to b4acb8fb119b0
(ec7a94e2a9)
- [LPD-24834] gradle-plugins-workspace: CreateClientExtensionConfigTask: update
LCP.json dependencies to reference the virtual instance-specific BSN
(14042d942f)
- [LPD-24834] gradle-plugins-workspace: adds test case for namespacing plugin
package dependencies (1a1348ba66)
- [LPD-24834] gradle-plugins-workspace: CreateClientExtensionConfigTask: updates
usages (068485e24b)
- [LPD-24834] gradle-plugins-workspace: ClientExtension: adds warning message
when the client extension and Gradle virtual instance id properties conflict
(849dd93503)
- [LPD-24834] gradle-plugins-workspace: ClientExtension: update usages
(935040e953)
- [LPD-24834] gradle-plugins-workspace: ClientExtensionProjectConfigurator:
update usages (8b74369c7a)
- [LPD-24834] gradle-plugins-workspace: WorkspaceExtension: adds setter for
virtualInstanceId (6c7fd8717c)
- [LPD-24834] gradle-plugins-workspace: WorkspaceExtension: only use the default
value where it is needed (573cdef14f)
- [LPD-24834] gradle-plugins-workspace: StringUtil: adds suffixIfNotBlank helper
method (52efc6997c)

## 10.1.10 - 2024-08-15

### Commits
- [LPD-24834] [workspace] baseline (ce926b3717)
- [LPD-24834] [workspace] add test cases (93a1099bb3)
- [LPD-24834] [workspace] add support for local deployment of CX bundles to
different virtual instances (60fd51670d)

### Dependencies
- [LPD-33240] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.146.

## 10.1.9 - 2024-08-12

### Commits
- [LPD-31541] Fix gradleTest classpath (fa3207ce37)
- [LPD-24745] Pin version (a3c381556d)
- [LPD-33272] Configure Theme CSS CX (0017658bdd)
- [LPD-33272] Source formatting (e6ad7dc764)
- [LPD-33272] gradle-plugins-workspace: SF: uses parens for consistency
(5750080944)
- [LPD-33272] gradle-plugins-workspace: SF: uses FileUtil.exists (97ff259844)
- [LPD-33272] Test that the RTL css files are also being generated (8cfb343410)
- [LPD-33272] Add missing gradle project required for testing (6e419177a1)
- [LPD-19346] Source formatting (b4acb8fb11)

### Dependencies
- [LPD-33244] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.145.

## 10.1.8 - 2024-08-07

### Commits
- [LPD-19346] gradle-plugins-workspace: moves deletion to a doFirst block to
avoid potential task ordering issues (f308e07f70)
- [LPD-19346] gradle-plugins-workspace: test: simplify assertions (5b0970aa39)
- [LPD-19346] gradle-plugins-workspace: test: uses copy task to copy dummy file
(c51c79a4ab)
- [LPD-19346] gradle-plugins-workspace: test: uses assemble block in the yaml
file (64bd6cb760)
- [LPD-19346] updateFileVersions (ac2ce375fa)
- [LPD-19346] add test case (2338156bde)
- [LPD-19346] clean build directory before assembleClientExtension (7410fa0366)
- [LPD-31478] Sort (febc610d81)
- [LPD-31478] Sort (b78d9ee7bf)
- [LPD-31478] workspace - add RTL version (e5fc5f0176)

### Dependencies
- [LRCI-4454] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.144.
- [LPD-32731] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.143.
- [LPD-26438] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.142.

## 10.1.7 - 2024-07-17

### Commits
- [LPD-31541] update com.fasterxml.jackson.dataformat:jackson-dataformat-yaml to
2.16.1 (5553baffcb)

### Dependencies
- [LPD-31541] Update the jackson-dataformat-yaml dependency to version 2.16.1.
- [LPD-5443] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.141.

## 10.1.6 - 2024-07-16

### Commits
- [LPD-24198] Workspace (98d7cde4a3)

### Dependencies
- [LPD-26455] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.140.
- [LPD-5443] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.139.

## 10.1.5 - 2024-07-08

### Commits
- [LPD-29733] Rename (966214b049)
- [LPD-29733] gradle-plugins-workspace: unrelated: test fixes (c48388e690)
- [LPD-29733] gradle-plugins-workspace: removes downloadReleasesJson task
(aed85a93cd)
- [LPD-29733] gradle-plugins-workspace: uses new workspace version (17e6771ee1)
- [LPD-29733] gradle-plugins-workspace: removes unused classes (68b6ccde3f)
- [LPD-29733] gradle-plugins-workspace: updates usages to new libraries
(8933f91c30)
- [LPD-29733] gradle-plugins-workspace: adds new libraries as dependencies
(cb0f366736)

### Dependencies
- [LPD-29733] Update the com.liferay.gradle.plugins.target.platform dependency
to version 4.0.1.
- [LPD-29733] Update the com.liferay.release.util dependency to version 1.0.0.
- [LPD-29733] Update the com.liferay.resource.util dependency to version 1.0.0.
- [LPD-30270] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.138.
- [LPD-28762] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.137.

## 10.1.4 - 2024-06-28

### Dependencies
- [LPD-28815] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.136.
- [LPD-29047] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.135.
- [LPD-27535] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.134.
- [LPD-28604] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.133.

## 10.1.3 - 2024-06-14

### Dependencies
- [LPD-28218] Update the com.liferay.ant.bnd dependency to version 3.2.12.
- [LPD-27502] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.132.
- [LPD-26470] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.131.
- [LPD-26999] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.130.
- [LPD-27355] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.129.
- [LPD-26157] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.128.

## 10.1.2 - 2024-05-29

### Commits
- [LPD-26792] gradle-plugins-workspace: excludes portal-impl and portal-kernel
from portalTestSnapshot configuration since they are not needed while running
unit tests (1a2ba05a44)
- [LPD-26792] gradle-plugins-workspace: SF: renames variables for consistency -
releasesMirror (90b1d27c8f)
- [LPD-26792] gradle-plugins-workspace: autogenerated: updateFileVersions
(ff0f9508ee)
- [LPD-26792] gradle-plugins-workspace: adds test case for ReleaseUtil reading
from provided mirrors (119a9406dd)
- [LPD-26792] gradle-plugins-workspace: allows trailing slash (17dbad729e)
- [LPD-26792] gradle-plugins-workspace: WorkspacePlugin: the property will
refresh the releases even with the daemon running (83d4cb3ff7)
- [LPD-26792] gradle-plugins-workspace: ReleaseUtil: allows configuring
additional mirrors for finding release information (939327a2ce)
- [LPD-26792] gradle-plugins-workspace: ResourceUtil: do not mess with the
timestamps of files that already exist (fa070a074c)
- [LPD-26792] gradle-plugins-workspace: ResourceUtil: makes logging easier to
follow (2bf8599b0d)

### Dependencies
- [LPD-7996] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.127.
- [LPD-25246] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.126.

## 10.1.1 - 2024-05-24

### Dependencies
- [LPD-25819] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.125.

## 10.1.0 - 2024-05-16

### Commits
- [LPD-21814] gradle-plugins-workspace: sort and collapse - order does not
matter here (8dd11bc532)
- [LPD-21814] gradle-plugins-workspace: fixes createDockerContainer test failure
(da8f96af1f)
- [LPD-21814] gradle-plugins-workspace: SF: lambdas can be safely used when not
a task action (e0b1eebd75)
- [LPD-21814] gradle-plugins-workspace: fixes jsPortlet test failure
(117ba7e6db)
- [LPD-21814] gradle-plugins-workspace: fixes Gradle 8 implicit dependency error
(90f606be93)
- [LPD-21814] gradle-plugins-workspace: SF: inlines name for consistency with
other methods (ad42f78000)
- [LPD-21814] gradle-plugins-workspace: fixes Gradle 8 implicit dependency error
(2d4f07bb81)

## 10.0.7 - 2024-05-16

### Commits
- [LPD-21814] gradle-plugins-workspace: bumps to 10.1.0 to indicate Gradle 8
compatibility (8d4d822926)
- [LPD-21814] gradle-plugins-target-platform: autogenerated: updateFileVersions
(1599c19e19)
- [LPD-21814] gradle-plugins-workspace: bump gradle-plugins version (6931f1d2f4)
- [LPD-21814] gradle-plugins-workspace: auto SF (17876bbc8e)
- [LPD-21814] gradle-plugins-workspace: bumps bnd dependencies to 6.4.0
(107a4d9b79)
- [LPD-22159] remove ThemeJS client extension from source code (88f2ae0996)
- [LPD-22405] Source formatting (a37a4e330b)
- [LPD-22405] Move (69ca578353)

### Dependencies
- [LPD-21814] Update the com.liferay.gradle.plugins.target.platform dependency
to version 4.0.0.
- [LPD-21814] Update the com.liferay.ant.bnd dependency to version 3.2.11.
- [LPD-21814] Update the biz.aQute.bnd.gradle dependency to version 6.4.0.

## 10.0.6 - 2024-04-09

### Commits
- [LPD-22405] gradle-plugins-workspace: removes test case that asserts on
validation (2af2f06d99)
- [LPD-22405] gradle-plugins-workspace: updates test case for no classification
(dfea8bc467)
- [LPD-22405] gradle-plugins-workspace: now treats the configuration
classification as the sensible defaults (39f32f3bbb)
- [LPD-22405] gradle-plugins-workspace: removes validation completely from
LCP.json overrides (a58e1ef924)

## 10.0.5 - 2024-04-06

### Commits
- [LPD-22405] Sort (245461379f)
- [LPD-22405] gradle-plugins-workspace: test: removes unused helper file
(5bb52caa8e)
- [LPD-22405] gradle-plugins-workspace: test: apply common script (73f2e72de5)
- [LPD-22405] gradle-plugins-workspace: test: adds common script for testing
expected file contents (69bf5b32f0)
- [LPD-22405] gradle-plugins-workspace: adds test case for the
no-client-extension case (efeb8ee872)
- [LPD-22405] gradle-plugins-workspace: returns unclassified if there are no
client extensions defined (35a2446126)

## 10.0.4 - 2024-04-05

### Commits
- [LPD-22405] gradle-plugins-workspace: adds test cases (5054ca856e)
- [LPD-22405] gradle-plugins-workspace: removes unused scripts (a0cc347b3e)
- [LPD-22405] gradle-plugins-workspace: apply to usages (39d6dd4abd)
- [LPD-22405] gradle-plugins-workspace: adds common script for the task should
fail test case (e865d6f55b)
- [LPD-22405] gradle-plugins-workspace: include paths to common test scripts as
project properties for use in test cases (ba76218440)
- [LPD-22405] gradle-plugins-workspace: CreateClientExtensionConfigTask: merges
LCP.json with template if both are found (aa028e10dc)
- [LPD-22405] gradle-plugins-workspace: StringUtil: adds helper method
(ee093b173c)
- [LPD-22405] gradle-plugins-workspace: apply new util to existing usage
(471efd8750)
- [LPD-22405] gradle-plugins-workspace: JsonNodeUtil: copiles JsonNode override
logic to new util class (7c4e2132bb)
- [LPD-22405] gradle-plugins-workspace: apply null checks to usages (f1a5071dd0)
- [LPD-22405] gradle-plugins-workspace: ResourceUtil: returns null instead of
throwing when the resource is not found (3694bc82fa)

## 10.0.3 - 2024-03-28

### Commits
- [LPD-6610] Wordsmith (cadbbed96f)
- [LPD-6610] Rename (e78a39d78d)
- [LPD-6610] Interface (2bab21663b)
- [LPD-6610] Wordsmith (5920055cef)
- [LPD-6610] Inline (1d29ad9067)
- [LPD-6610] Wordsmith (a9bfe4b363)
- [LPD-6610] gradle-plugins-workspace: test: adds test case for the empty file
default (e5f2cdb43c)
- [LPD-6610] gradle-plugins-workspace: throws more helpful exception if the JSON
is invalid (4239ba6422)
- [LPD-6610] gradle-plugins-workspace: defaults to empty JSON object if the
declared file has no content (864e33a147)
- [LPD-6610] gradle-plugins-workspace: ensures that changes to the
frontendTokenDefinitionJSON file are always processed (df8195ddd6)
- [LPD-6610] gradle-plugins-workspace: ResourceUtil: throws an exception if
there is an issue processing the inputStream (09af7fca1b)
- [LPD-6610] gradle-plugins-workspace: unrelated: simplify logic using
ResourceUtil (4820cf1321)
- [LPD-6610] gradle-plugins-workspace: uses the existing JSON library for
consistency (0a428c8ca9)
- [LPD-6610] gradle-plugins-workspace: test: consoldates test cases to re-use
logic (cff360b6b4)
- [LPD-6610] gradle-plugins-workspace: refactor: groups clientExtension type
checks together (d54529d5fe)
- [LPD-6610] gradle-plugins-workspace: refactor: inline the client extension
properties reads into ClientExtension (2e7ac57c0b)
- [LPD-6610] gradle-plugins-workspace: uses Object.equals for consistency
(0ee61ba4cf)
- [LPD-6610] - Rename for consistency. (bfc9761055)
- [LPD-6610] gradle-plugins-workspace - inline frontend token definition into
client extension config json file (777880027e)
- [LPD-20533] Categorize jsImportMapsEntry CX as frontend (ac4501c963)
- [LPD-7009] gradle-plugins-workspace: build: removes redundant plugin apply
(c2fa1b12d5)
- [LPD-7009] gradle-plugins-workspace: removes incorrect statement (22ab914ff4)
- [LPD-7009] gradle-plugins-workspace: test: fixes method not found failure
during test runs (81772d2fc2)
- [LPD-1783] Wordsmith (1e4cbb3b17)
- [LPD-1783] ant update-portal-osgi-configuration-properties (8c8a280870)

## 10.0.2 - 2024-03-21

### Commits
- [LPD-7009] Make this a sentence. How come we don't have other descriptions?
(ec2f4b78df)
- [LPD-7009] gradle-plugins-workspace: test: uses gradle api for getting the
file (fcd9625a99)
- [LPD-7009] gradle-plugins-workspace: test: no need for deploy (9a11ccc206)
- [LPD-7009] gradle-plugins-workspace: test: updates expected values
(1df04c37cf)
- [LPD-7009] gradle-plugins-workspace: wordsmith (e56286224f)
- [LPD-7009] add gradle tests to cover GlobalJS Client Extension validation
(93c715914b)
- [LPD-7009] validate <script/> element attributes for workspace GlobalJS Client
Extension (2a5e73b95c)
- [LPD-15162] Move tests (f23b048eff)
- [LPD-15162] Update README.markdown (5e6c766cd3)
- [LPD-15162] Update plugins Gradle version (7757c65773)

### Dependencies
- [LPD-18681] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.108.

## 10.0.1 - 2024-03-15

### Commits
- [LPD-15162] Workspace (523b2dbb4c)
- [LPD-7009] Regen (d68f2af65f)

### Dependencies
- [LPD-15162 LPD-8052] Update the com.liferay.gradle.plugins.target.platform
dependency to version 3.0.34.
- [LPD-8052] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.107.

## 10.0.0 - 2024-03-14

### Dependencies
- [LPD-16636] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.33.

## 9.1.6 - 2024-03-14

### Commits
- [LPD-16636] gradle-plugins-workspace: autogenerated: baseline (c6f26380d5)
- [LPD-16636] gradle-plugins-workspace: test: verifyProductException - updates
assertion (b29d1d9a67)
- [LPD-16636] gradle-plugins-workspace: test: verifyProduct - updates version
(f245bf589a)
- [LPD-16636] gradle-plugins-workspace: test:
verifyCustomBundleChecksumSHA512Skip - updates version (b98e4a3fa0)
- [LPD-16636] gradle-plugins-workspace: test: verifyBundleChecksumSHA512 -
updates version and checksum (f4464aa1f5)
- [LPD-16636] gradle-plugins-workspace: test: verifyBundleChecksumBad - updates
version and checksum (06886acb99)
- [LPD-16636] gradle-plugins-workspace: test: upgradePluginsSDK - updates
version (e1938f36ec)
- [LPD-16636] gradle-plugins-workspace: test: themesYarnBuild - updates version
(a7829b21ae)
- [LPD-16636] gradle-plugins-workspace: test: themesNodeBuild - updates version
(0a4c800a32)
- [LPD-16636] gradle-plugins-workspace: test: themesJavaBuild - updates version
(ed04f5252d)
- [LPD-16636] gradle-plugins-workspace: test: startDockerContainerWithScripts -
updates version (140d4535d9)
- [LPD-16636] gradle-plugins-workspace: test: startDockerContainerWithScript -
updates version (0d42aadb55)
- [LPD-16636] gradle-plugins-workspace: test: startDockerContainer - updates
version (103a7070f6)
- [LPD-16636] gradle-plugins-workspace: test: relativeUrl - updates version
(b3af59f352)
- [LPD-16636] gradle-plugins-workspace: test: pruneDockerImage - updates version
(9d2db01857)
- [LPD-16636] gradle-plugins-workspace: test: providedModules - updates version
(7d6c4ac632)
- [LPD-16636] gradle-plugins-workspace: test: productInfoDefaultValues - updates
version and expected values (5becf6520d)
- [LPD-16636] gradle-plugins-workspace: test: jsPortlet - updates version
(ce98904f1b)
- [LPD-16636] gradle-plugins-workspace: test: testInitBundle - early return
until there is a good way to inspect 7z archives from gradle (3ed4c9a75d)
- [LPD-16636] gradle-plugins-workspace: test: initBundle - updates versions
(8ce30b0c40)
- [LPD-16636] gradle-plugins-workspace: test: downloadFileURL - updates version
(ccf1bc7033)
- [LPD-16636] gradle-plugins-workspace: test: downloadBundleSkipWithCache -
updates version (db7fe8bb38)
- [LPD-16636] gradle-plugins-workspace: test: dockerfileExt - updates version
(a86e3a4b2e)
- [LPD-16636] gradle-plugins-workspace: test:
distBundleZipWithEnvironmentDistMetaData - updates version (e9c71efd5a)
- [LPD-16636] gradle-plugins-workspace: test: distBundleZipWithEnvironment -
updates version (1fe6c33e6e)
- [LPD-16636] gradle-plugins-workspace: test: distBundleZipAll - updates version
(514beebf4b)
- [LPD-16636] gradle-plugins-workspace: test: distBundleZip - updates version
and assertions (05cc0b03f6)
- [LPD-16636] gradle-plugins-workspace: test: distBundleCopyConfigs - updates
version and expected values (38752e594d)
- [LPD-16636] gradle-plugins-workspace: test: designPack - updates version
(1fffaba928)
- [LPD-16636] gradle-plugins-workspace: test: customCacheDir - updates version
(246907f802)
- [LPD-16636] gradle-plugins-workspace: test: createDockerContainer - updates
version (dffef2e0fb)
- [LPD-16636] gradle-plugins-workspace: test: buildDockerImageNoConfigs -
updates version (4e73b4c6a8)
- [LPD-16636] gradle-plugins-workspace: test: buildDockerImage - updates version
(bad2bf9ae2)
- [LPD-16636] gradle-plugins-workspace: build.gradle: increases test gradle
daemon memory limit (e537ad62b7)
- [LPD-16636] gradle-plugins-workspace: RootProjectConfigurator: fix 7zip
extraction for distBundle (9cbaf5b9ba)
- [LPD-16636] gradle-plugins-workspace: applies max age timeout pattern to the
node info file (9944415bc8)
- [LPD-16636] gradle-plugins-workspace: embeds the releases.json file into the
jar (bc72cc8ac7)
- [LPD-16636] gradle-plugins-workspace: WorkspaceExtension: removes unnecessary
suppression (d81fc8ebaa)
- [LPD-16636] gradle-plugins-workspace: WorkspaceExtension: removes unused
fields (143bc0ba6e)
- [LPD-16636] gradle-plugins-workspace: removes dependency on petra modules
(2cff1cd90c)
- [LPD-16636] gradle-plugins-workspace: renames MD5 test case dirs to SHA512
(0762bb0ee5)
- [SHA-512] (596e51157c)
- [SHA-512] for the VerifyBundle algorithm (db7cc6a313)
- [LPD-16636] gradle-plugins-workspace: removes dependency on Gson (7205315171)
- [LPD-16636] gradle-plugins-workspace: uses Jackson annotations in favor of
Gson (8fcd16636e)
- [LPD-16636] gradle-plugins-workspace: RootProjectConfigurator: simplify
(c35ce463c2)
- [LPD-16636] gradle-plugins-workspace: RootProjectConfigurator: uses
ReleaseUtil (9e8a9cae48)
- [LPD-16636] gradle-plugins-workspace: VerifyProductTask: simplify (d06ad8271e)
- [LPD-16636] gradle-plugins-workspace: WorkspaceExtension: uses new ReleaseUtil
(04bbc9fda3)
- [LPD-16636] gradle-plugins-workspace: adds ReleaseUtil to handle retrieving
and parsing release properties info (0073757a5f)
- [LPD-16636] gradle-plugins-workspace: adds more helper methods to ResourceUtil
(27eccd8511)

## 9.1.5 - 2024-03-08

### Commits
- [LPD-19659] Sort (f9eca162eb)
- [LPD-19659] Hashify (03490e2027)
- [LPD-17470] Update all jsons examples in Liferay portal (f85a79fb35)

### Dependencies
- [LPD-6870] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.106.
- [LPS-205214] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.105.
- [LPD-19220] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.104.
- [LPD-17973] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.103.
- [LPD-17973] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.102.
- [LPD-18565] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.101.
- [LPD-18192] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.100.
- [LPD-8724] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.99.

## 9.1.4 - 2024-02-12

### Commits
- [LPD-16391] gradle-plugins-workspace: removes test case for removed check
(eb4fe812dd)
- [LPD-16391] gradle-plugins-workspace: removes unnecessary check since if the
destination file exists already, the task will be skipped (fd7f00e174)
- [LPD-16391] gradle-plugins-workspace: adds test case (779731fd18)
- [LPD-16391] gradle-plugins-workspace: skips the downloadBundle task if the
file is already in the cache (9340fc4e97)
- [LPD-16384] Apply usages (4363bb8b79)
- [LPD-16079] Oracle calls it class path in
https://docs.oracle.com/javase/8/docs/technotes/tools/windows/classpath.html
(63665cc450)

### Dependencies
- [LPD-16391] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.32.
- [LPD-15285] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.98.
- [LPS-206269] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.97.
- [LPD-16079] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.31.

## 9.1.3 - 2024-02-01

### Commits
- [LPD-16079] Wordsmith (b5a8e902ae)
- [LPD-16079] gradle-plugins-workspace: apply static util to
LiferayWorkspaceNodePlugin (a8e60569f4)
- [LPD-16079] gradle-plugins-workspace: apply static util to WorkspaceExtension
(38402ee043)
- [LPD-16079] gradle-plugins-workspace: creates ResourceUtil to allow reuse
(08387f5c7d)
- [LPD-16079] gradle-plugins-workspace: WorkspaceExtension: only retrieves the
product info once in the constructor (cfc897f5ba)

## 9.1.2 - 2024-01-31

### Commits
- [LPS-206278] gradle-plugins-workspace: apply to distBundle task (7f6aaa279e)
- [LPS-206278] gradle-plugins-workspace: since the bundle support will have
already copied the configs, just copy around in the bundle (ed7fd9c1f7)
- [LPS-206278] gradle-plugins-workspace: adds test cases for the distBundle case
(52e7eeb74d)
- [LPS-206278] gradle-plugins-workspace: updates initBundle test cases to assert
both versioned and unversioned tomcat directories (07282da833)
- [LPS-206195] manually upgrade com.fasterxml.jackson.core to 2.16.1
(38454eec2d)

### Dependencies
- [LPS-206278] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.30.

## 9.1.0 - 2024-01-25

### Commits
- [LPS-193787] Wordsmith (1de3b13387)

### Dependencies
- [LPS-193787] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.29.

## 9.0.24 - 2024-01-25

### Commits
- [LPS-193787] gradle-plugins-workspace: autogenerated: baseline (adb7a06b17)
- [LPS-193787] gradle-plugins-workspace: applies new plugin (01bed3d057)
- [LPS-193787] gradle-plugins-workspace: adds LiferayWorkspaceNodePlugin to
encapsulate LTS resolution logic (c2a396fcb6)
- [LPS-193787] gradle-plugins-workspace: adds test cases (5ee8276003)

### Dependencies
- [LPS-205913] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.96.

## 9.0.23 - 2024-01-22

### Commits
- [LPS-203434] gradle-plugins-workspace: regen instance settings json schema
(50d6e3ae9c)
- [LPS-203434] gradle-plugins-workspace: references sub-schema (f0d3ccb9a6)

### Dependencies
- [COMMERCE-12719] Update the com.liferay.gradle.plugins.source.formatter
dependency to version 5.2.95.
- [LPS-206396] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.94.

## 9.0.22 - 2024-01-18

### Commits
- [COMMERCE-12722] added new Commerce Checkout Step type (f4488d0a4e)
- [LPS-105380] Clean up (a4d06b9daa)

### Dependencies
- [LPS-204572] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.93.
- [LPS-204924] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.92.

## 9.0.21 - 2024-01-12

### Dependencies
- [POSHI-688] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.91.
- [LPS-204967] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.90.

## 9.0.20 - 2024-01-10

### Commits
- [LPS-205476] gradle-plugins-workspace: manually adjust up-to-date status when
assemble source changes (ff6cdb1894)

### Dependencies
- [LPS-205476] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.28.

## 9.0.19 - 2024-01-06

### Commits
- [LPS-204423] Wordsmith (60247827e1)
- [LPS-204423] gradle-plugins-workspace: if no provided or template file is
found, fail the build with a detailed exception (99636c04a2)
- [LPS-204423] gradle-plugins-workspace: adds test cases (d684bbdd7e)
- [LPS-202509] Source formatting (016db48e89)

### Dependencies
- [LPS-204423] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.27.
- [LPS-204953] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.89.
- [LPS-201781] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.88.
- [LPS-204321] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.87.

## 9.0.18 - 2023-12-24

### Commits
- [LPS-202509] gradle-plugins-workspace: adds clarifying assertion text
(2367f909fd)
- [LPS-202509] gradle-plugins-workspace: adds test case for expanding urls with
parameters (364aa35653)
- [LPS-202509] gradle-plugins-workspace: refactor glob expansion test cases to
allow for easier assertion (92fab6c9a7)
- [LPS-202509] gradle-plugins-workspace: splits out the query string from the
glob, then re-adds the query string after the paths are expanded (51efb06c41)
- [LPS-202509] gradle-plugins-workspace: adds test case for the buildTimestamp
field (8b4828bd59)

### Dependencies
- [LPS-202509] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.26.

## 9.0.17 - 2023-12-24

### Commits
- [LPS-203834] Match other descriptions for now, and mass auto change in the
future (a3288b871a)
- [LPS-203834] Copy description from UI (7584fbb1f6)
- [LPS-203834] Add Editor Config Contributor CET type (96917f86af)
- [LPS-195925] reverted, so republished (230552bb74)

### Dependencies
- [LPS-203834] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.25.
- [LPS-204449] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.86.

## 9.0.16 - 2023-12-21

### Commits
- [LPS-202509] Dev Workspace: Add a substitution variable containing the build
time that can be used in CX URLs (ec8b44d2c5)

### Dependencies
- [LPS-202509] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.24.
- [LPS-203981] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.85.

## 9.0.15 - 2023-12-14

### Commits
- [LPS-203804] gradle-plugins-workspace: simplify test case (bf98969396)
- [LPS-203804] gradle-plugins-workspace: removes unnecessary check (21a3e13805)
- [LPS-203804] gradle-plugins-workspace: SF - buy space and variable naming
(1f4f927535)
- [LPS-203804] support quarter release target platform version (4c49ecece2)

### Dependencies
- [LPS-203804] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.23.
- [LPS-203145] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.84.
- [POSHI-686] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.83.
- [LPS-190915] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.82.

## 9.0.14 - 2023-12-08

### Dependencies
- [LPS-203507] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.81.
- [LPS-190915] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.80.
- [LPS-200543] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.79.
- [LPS-203017] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.78.

## 9.0.13 - 2023-12-02

### Commits
- [LPS-202874] gradle-plugins-workspace: forces no version on the luffa
(74be7092c9)
- [LPS-202874] gradle-plugins-workspace: updates assertion since the zip should
never have a version in it (19bdbc41ba)
- [LPS-202874] gradle-plugins-workspace: avoids chaining (a686699f8e)
- [LPS-202874] gradle-plugins-workspace: adds client-extension suffix to test
projects (e4eded353d)
- [LPS-202874] gradle-plugins-workspace: adds test case to reproduce the issue
(b617bbff0c)
- [LPS-202874] gradle-plugins-workspace: just deploys the output of the build
zip task (476153b974)
- [COMMERCE-12879] workspace client extension properties (789d9fc4c0)
- [LPS-202307] Wordsmith (d62031e3bc)
- [LPS-202307] gradle-plugins-workspace: adds client-extension.schema.json
(85e78d1aa0)
- [LPS-201858] gradle-plugins-workspace: renames test files and text to generic
values, and trims down the json file (f78cfa7ebe)

### Dependencies
- [LPS-20287] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.22.
- [LPS-199623] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.77.
- [LPS-200190] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.76.
- [LPS-200552] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.75.
- [LPS-199324] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.74.

## 9.0.12 - 2023-11-16

### Commits
- [LPS-201858] #sfme more here (dfb3d14f7a)
- [LPS-201858] #sfme please enforce xyzJsonNode (fbb999b0ec)
- [LPS-201858] #sfme please enforce xyzJsonNode (aba6006e6f)
- [LPS-201858] gradle-plugins-workspace: process the JSON files in a batch
project (4ef5a2055b)
- [LPS-201858] gradle-plugins-workspace: adds test case for the base64 encoding
(cfa3ac329b)
- [LPS-121480] Use jQuery 3.5.1 to avoid vulnerability (f8cfd8196f)

### Dependencies
- [LPS-201858] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.21.

## 9.0.11 - 2023-11-08

### Commits
- [LPS-200966] gradle-plugins-workspace: conditionally sets the
site-initializer.json file (2d9a204171)
- [LPS-200966] gradle-plugins-workspace: adds test case to assert that a
site-initializer output dir is not created if the project does not contain a
siteInitializer cx (8ff06cf32b)

### Dependencies
- [LPS-153494] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.73.

## 9.0.10 - 2023-11-06

### Commits
- [LPS-196455] gradle-plugins-workspace: partial revert of 919f5e4392781
(28bb0dedda)
- [LPS-196455] gradle-plugins-workspace: removes docker-java dependency since
the version conflicts with the one pulled in with the gradle docker dependency
(4a2ea85daa)
- [LPS-196455] gradle-plugins-workspace: uses method to add exclude patterns
instead of setting them (dd15b6a843)

### Dependencies
- [LPS-198875] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.72.

## 9.0.9 - 2023-11-01

### Commits
- [LPS-183167] gradle-plugins-workspace: matches the Java type for task
configuration variable names (8b0249f532)
- [LPS-183167] gradle-plugins-workspace: uses Task instead of DefaultTask
(bd77a2d916)
- [LPS-183167] gradle-plugins-workspace: uses Java class name for taskOutputs
variables (d0039c899d)
- [LPS-183167] gradle-plugins-workspace: uses Java class name for taskInputs
variables (5997e775c2)
- [LPS-183167] gradle-plugins-workspace: reverts to the old pattern (fe5f7e982a)
- [LPS-155917] fix workspace plugin gradleTest error (919f5e4392)
- [LPS-155917] update workspace angular test (3ccde1ef06)

### Dependencies
- [LPS-200165] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.71.

## 9.0.8 - 2023-10-30

### Commits
- [LPS-183167] gradle-plugins-workspace: baseline (2c6a17fc59)
- [LPS-183167] gradle-plugins-workspace: conditionally sets the profile yaml
files as inputs so that switching between deploy commands will make tasks out of
date (37a0956b1c)
- [LPS-183167] gradle-plugins-workspace: uses helper methods to set inputs and
outputs on task providers (6459ba9d30)
- [LPS-183167] gradle-plugins-workspace: adds helper method to set outputs to
always up to date for validation methods that produce no output (9569d063e0)
- [LPS-183167] gradle-plugins-workspace: adds helper method to conditionally add
an input file to multiple TaskProviders (82f3dc1430)
- [LPS-183167] gradle-plugins-workspace: replaces lambdas with anonymous classes
so that Gradle can do accurate up-to-date checking (66a54f43ee)
- [LPS-183167] gradle-plugins-workspace: only depend on the site initializer
task when it will actually be needed (a9a8da4755)
- [LPS-183167] gradle-plugins-workspace: fixes incorrect input annotations to
use OutputFile (83a3e1df89)
- [LPS-196455] gradle-plugins-workspace: removes helper properties (630f240ef1)

## 9.0.7 - 2023-10-30

### Commits
- [LPS-121481] Regenerate package-lock.json (39462d168c)
- [LPS-121481] Use highcharts 11.1.0 to avoid vulnerability (61052605c3)
- [LPS-196539] SF, rename (4f33caca1b)

### Dependencies
- [POSHI-659] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.70.
- [LPS-199457] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.69.

## 9.0.6 - 2023-10-26

### Dependencies
- [POSHI-557] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.68.

## 9.0.5 - 2023-10-24

### Commits
- [LPS-197368] gradle-plugins-workspace: adds assertion for the new zip
structure (545d50f4e9)
- [LPS-197368] gradle-plugins-workspace: auto SF (da82f7feca)
- [LPS-197368] make sure to include a "site-initializer" dir inside the zip
(09320fc5c9)
- [LPS-197368] gradle-plugins-workspace: removes "-cx" suffix from gradleTest
cases (351e4d979c)
- [LPS-199271] gradle-plugins-workspace: adds assertion that the policy
statement is inside of individual configuration blocks rather than at top level
(3bddde4d79)
- [LPS-199271] the force policy for OSGi configurations is set in the wrong
place (2d9b0d7a79)
- [LPS-191500] Updating com.google.template:soy to version 2019-03-07
(9a9862aa0a)

### Dependencies
- [LPS-197623] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.67.
- [LPS-199421] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.66.
- [LPS-199421] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.65.
- [LPS-199563] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.64.
- [LPS-181508] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.63.

## 9.0.4 - 2023-10-17

### Commits
- [LPS-197368] gradle-plugins-workspace: SF: easier to read (29e4231d5e)
- [LPS-197368] gradle-plugins-workspace: unrelated: no need to open the
FileReader (522ec854ba)
- [LPS-197368] gradle-plugins-workspace: implement (ac129be56c)
- [LPS-197368] gradle-plugins-workspace: adds helper method in StringUtil to
quote strings (cb951bf292)
- [LPS-197368] gradle-plugins-workspace: adds parameter to batch Dockerfile
template (443d14ae6a)
- [LPS-197368] gradle-plugins-workspace: client-extension.properties: changes
the site-initializer classification (b1a14d7bc2)
- [LPS-197368] gradle-plugins-workspace: adds helper logic and properties to
build.gradle for gradleTest (9728cf7442)
- [LPS-197368] gradle-plugins-workspace: adds test cases (f8e17369b4)

### Dependencies
- [LPS-197368] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.20.
- [LPS-198717] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.62.
- [LPS-197901] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.61.
- [LPS-192413] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.60.
- [LPS-180050] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.59.
- [LPS-196218] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.58.
- [LPS-196617] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.57.

## 9.0.3 - 2023-10-03

### Dependencies
- [LPS-197582] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.56.

## 9.0.2 - 2023-09-23

### Commits
- [LPS-195564] Wordsmith (7b3c593cc0)
- [LPS-195564] gradle-plugins-workspace: be more selective in which tasks get
the environment variables (9c2768084f)
- [LPS-195564] gradle-plugins-workspace: if the task has an environment method,
inject the two route values as environment variables (2d97731ca4)
- [LPS-195564] gradle-plugins-workspace: adds test cases to verify the expected
behavior (f848cb7771)

### Dependencies
- [LPS-195564] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.55.
- [LPS-195564] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.19.

## 9.0.1 - 2023-09-20

### Dependencies
- [POSHI-645] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.54.

## 8.0.9 - 2023-09-13

### Commits
- [LPS-195170] major version bump is fine (0a5412f6a1)
- [LPS-195170] gradle-plugins-target-platform: updateFileVersions (52ecf06869)
- [LPS-195170] gradle-plugins-workspace: updateFileVersions (d60f7c9533)
- [LPS-195170] gradle-plugins-workspace: use gradle plugin 14.0.177 in workspace
plugin (9b2d9f81a5)
- [LPS-195170] fix spelling error (2f25226599)

### Dependencies
- [LPS-195170] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.18.
- [LPS-194818] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.53.

## 8.0.8 - 2023-09-06

### Commits
- [LPS-193507] gradle-plugins-workspace: updateFileVersions (26f8c293d8)
- [LPS-193507] gradle-plugins-workspace: adds basic test name output to
gradleTest task (d7790a7112)
- [LPS-193507] gradle-plugins-workspace: fixes VerifyProductException test
failure (1e3995732c)
- [LPS-193507] gradle-plugins-workspace: fixes TestIntegrationClasses test
failure (990aa2292f)
- [LPS-193507] gradle-plugins-workspace: only applies the theme configurer once
after all yaml files have been processed (4e4c17f8c3)
- [LPS-193507] gradle-plugins-workspace: (unrelated) removes useless var
(3ed9242f0a)

### Dependencies
- [LPS-193507] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.17.

## 8.0.7 - 2023-08-30

### Commits
- [LPS-194218] gradle-plugins-workspace: updateFileVersions (dd09090c35)

### Dependencies
- [POSHI-637] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.52.
- [LPS-194218] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.16.
- [LPS-194134] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.51.
- [LPS-193777] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.50.

## 8.0.6 - 2023-08-22

### Commits
- [LPS-184062] Source formatting (af9514615f)

## 8.0.5 - 2023-08-22

### Commits
- [LPS-184062] Sort (a964be1465)
- [LPS-184062] respect the workspace extension docker pull policy and registry
(18d3370555)

## 8.0.4 - 2023-08-22

### Commits
- [LPS-190136] Add support for objectValidationRule type (89d3d18814)
- [LPS-193534] Generated by SF. (b6543fa61e)

### Dependencies
- [LPS-187428] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.49.

## 8.0.3 - 2023-08-17

### Commits
- [LPS-181508] liferay-portal: updateFileVersions (c87bf45b60)
- [LPS-187069] Liferay Routes Implementation and Tests (864f153483)

### Dependencies
- [LPS-181508] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.15.
- [LPS-193473] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.48.

## 8.0.2 - 2023-08-16

### Commits
- [LPS-188758] wordsmith (10e33c48d8)
- [LPS-188758] fix jsModuleConfigGenerator gradleTest error (72f3508189)
- [LPS-188758] gradle-plugins-workspace: spelling (9481d18b54)
- [LPS-188758] gradle-plugins-workspace: just use isNull checks for clarity
(060714a953)
- [LPS-188758] fix gradleTest error (b7f1a1d978)
- [LPS-188758] only verify when user do not override default product value
(45b84c929d)
- [LPS-188758] add gradleTest for verifyProduct task (d575376aef)
- [LPS-188758] apply verifyProduct when user set product key (300040459e)

## 8.0.1 - 2023-08-15

### Dependencies
- [POSHI-610] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.47.

## 8.0.0 - 2023-08-13

### Dependencies
- [LPS-181508] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.14.

## 7.0.3 - 2023-08-12

### Commits
- [LPS-181508] gradle-plugins-workspace: major workspace version bump to
indicate that the move to gradle 7 is a breaking change (ca79d686f4)
- [LPS-187069] Revert "LPS-187069 Update Liferay Sample Workspace" (2076a873a1)
- [LPS-187069] Update Liferay Sample Workspace (91a888a8a7)

### Dependencies
- [LPS-186127] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.46.

## 7.0.2 - 2023-08-04

### Commits
- [LPS-181508] fix duplicatesStrategy for initBundle (12ce72508a)
- [LPS-181508] Remove dependencies (7476be980b)
- [LPS-181508] Source formatting (8e9df82226)
- [LPS-181508] updage jackson for macos (91514ffa3e)
- [LPS-181508] fix jackson error on windows and macos (544f2650f0)
- [LPS-181508] update gradleTest for windows (d889c373dd)
- [LPS-181508] remove jdk 8 compatibilty setting for workspace plugin
(bf61add078)
- [LPS-181508] fix workspacePlugin targetPlatform gradleTest error (9d1c2ec6a6)
- [LPS-181508] fix productInfoDefaultValues and productInfoOverrideDefaults
gradleTest (3d5cb67902)
- [LPS-181508] fix targetplatform gradleTest (917bc7ff1b)
- [LPS-181508] fix relativeUrl gradleTest error (60c3347543)
- [LPS-181508] correctly set relative path bundle file for initBundleTask
(a8d74fa1f2)
- [LPS-181508] Sort (04f53a9c4c)
- [LPS-181508] Order tasks (7c904e6712)
- [LPS-181508] use internal annotation for imageId of DockerListImageTask
(2c032a2c6d)
- [LPS-181508] set include DuplicatesStrategy for dockerDeployTask (701d959f5f)
- [LPS-181508] buildDockerImage should depnedon dockerDeploy task (df92a00333)
- [LPS-181508] make sure firstly remove container before image (8b7c36837d)
- [LPS-181508] revert clientExtension test changes (19fd3dc3a2)
- [LPS-181508] fix inputFile annotation error for ConfigJSModulesTask
(1bd04897b3)
- [LPS-181508] copy depedencies to gradleTest repo (c3b025417b)
- [LPS-181508] add missed input parameter annotation for ConfigJSModulesTask
(e69afe2b05)
- [LPS-181508] inputFile annotation for TranspileJSTask (6769fbafcd)
- [LPS-181508] input annotation for TranspileJSTask (c83834e27a)
- [LPS-181508] make copyDistBundleZipPluginsSdk, copyHotFixZip and
copyGradleTestProjects finalizedBy gradleTestGenerator (b7a851fef5)
- [LPS-181508] Move to workspace (c295b2db72)
- [LPS-181508] Fix tests issues (f9501322bf)
- [LPS-181508] InputFiles anotation and SF (be12c0d698)
- [LPS-181508] remove PathSensitive for PackageJsonFile (71e7f40aab)
- [LPS-181508] set duplicatesStrategy for dockerDeploy task (3ed8d7c255)
- [LPS-181508] Download (05b5afa588)
- [LPS-181508] Update README.markdown (c9f00ca654)
- [LPS-181508] Update plugins Gradle version (60571c128e)
- [LPS-181508] Annotations (beddb5d22a)
- [LPS-181508] Fix soy (24b9d84d34)
- [LPS-181508] Versions (0362751ca7)
- [LPS-181508] Fix tests (05eb9088d3)
- [LPS-181508] Copy (f6124575e5)
- [LPS-181508] Runtime (47c8bf722f)
- [LPS-181508] Manual (c689a849e6)
- [LPS-181508] Auto SF (runtime) (5e7a1385d1)
- [LPS-181508] Runtime (203d66906c)
- [LPS-181508] Runtime (7b88c6ea99)
- [LPS-181508] Apply java-library (d3c0c00f02)

### Dependencies
- [LPS-181508] Update the com.liferay.ant.bnd dependency to version 3.2.10.
- [LPS-181508] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.45.
- [LPS-181508] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.13.
- [LPS-181508] Update the jackson-databind dependency to version 2.12.5.
- [LPS-181508] Update the jackson-dataformat-yaml dependency to version 2.12.5.
- [LPS-181508] Update the jackson-dataformat-yaml dependency to version 2.12.5.
- [LPS-181508] Update the jackson-databind dependency to version 2.12.5.
- [LPS-181508] Update the jackson-annotations dependency to version 2.12.5.
- [LPS-181508] Update the jackson-core dependency to version 2.12.5.
- [LPS-181508] Update the jackson-databind dependency to version 2.10.3.
- [LPS-181508] Update the jackson-annotations dependency to version 2.10.3.
- [LPS-181508] Update the jackson-core dependency to version 2.10.3.
- [LPS-181508] Update the biz.aQute.bnd.gradle dependency to version 5.3.0.
- [LPS-181508] Update the asm dependency to version 5.1.
- [LPS-181508] Update the gradle-download-task dependency to version 5.4.0.
- [LPS-181508] Update the gradle-docker-plugin dependency to version 6.7.0.
- [LPS-181508] Update the jackson-dataformat-yaml dependency to version 2.10.3.
- [LPS-181508] Update the docker-java dependency to version 3.2.8.
- [LPS-181508] Update the com.liferay.ant.bnd dependency to version 3.2.9.
- [LPS-181508] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.44.
- [LPS-181508] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.12.
- [LPS-181508] Update the com.liferay.petra.lang dependency to version 5.1.2.
- [LPS-181508] Update the com.liferay.petra.string dependency to version 5.2.0.
- [LPS-181508] Update the gradle-download-task dependency to version 5.0.0.
- [LPS-181508] Update the jna dependency to version 5.8.0.
- [LPS-181508] Update the gradle-properties-plugin dependency to version 1.4.6.
- [LPS-190915] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.44.

## 7.0.1 - 2023-08-01

### Dependencies
- [POSHI-604] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.43.

## 6.1.16 - 2023-08-01

### Commits
- [LPS-192082] Source formatting (932537b9e3)
- [LPS-192082] Sort (417be00b1d)
- [LPS-192082] Follow existing pattern (6c3e94c5b8)
- [LPS-192082] gradle-plugins-workspace: (unrelated) fixes doc link warning
(6df956082a)
- [LPS-192082] gradle-plugins-workspace: directly invokes InitBundleCommand
instead of using a JavaExec (51ea80167a)

### Dependencies
- [POSHI-599] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.42.

## 6.1.15 - 2023-07-21

### Commits
- [LPS-188912] gradle plugins workspace (637e5951e4)

### Dependencies
- [LPS-188912] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.41.

## 6.1.14 - 2023-07-19

### Commits
- [LPS-190887] gradle-plugins-workspace: updateFileVersions (94692faa80)
- [LPS-190887] gradle-plugins-workspace: updates file versions match pattern
(df35af078d)
- [COMMERCE-11180] add commercePaymentIntegration client extension property
(8825c7f55b)

### Dependencies
- [LPS-190887] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.12.
- [LPS-187692] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.40.
- [LPS-189252] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.39.

## 6.1.13 - 2023-07-10

### Commits
- [LPS-186063] reverted, and republished (b179388108)
- [LPS-186063] Revert "LPS-186063 Can we do it in this order?" (342a5f017f)

### Dependencies
- [POSHI-587] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.38.

## 6.1.12 - 2023-07-07

### Commits
- [LPS-186063] Can we do it in this order? (ebe302087c)
- [LPS-186063] Wordsmith (775dc64918)
- [LPS-186063] sort (cc41ecdd4c)
- [LPS-186063] add plugin "name" and "module-group-id" to honour
PluginPackageUtil validation (a597b49bf9)
- [LPS-186063] always process files in case they contain substitutions
(9cde7ba89a)
- [LPS-186063] set the bsn of client extension bundles to projectId so that it
maps to LCP.json dependencies (005832ed35)
- [LPS-186063] add projectId property (df55b4c84f)
- [LPS-186063] this should actually be called projectId (1404e004ec)
- [LPS-186063] convert LCP.json dependencies into required-deployment-contexts
(a8e610e965)
- [LPS-186063] add method to convert dependencies from LCP.json to
required-deployment-contexts pluginPackageProperties (004848361c)
- [LPS-186063] move store pluginPackageProperties after LCP.json has been
processed (cd75ec5fe0)

## 6.1.11 - 2023-07-07

### Commits
- [LPS-188564] gradle-plugins-workspace: if no glob paths are matched, fail the
build (3a8f091100)

## 6.1.10 - 2023-07-04

### Commits
- [LPS-188465] gradle-plugins-workspace: adds default livenessProbe and
readinessProbe to the frontend template (5cf88b44e2)

## 6.1.8 - 2023-06-29

### Dependencies
- [POSHI-570] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.37.
- [LPS-170503] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.36.
- [LPS-186127] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.35.
- [LPS-186060] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.34.

## 6.1.7 - 2023-06-20

### Commits
- [LPS-184016] - Rename to FDSFilter (02d840d92f)
- [LPS-184016] - Create dataSetFilter client extension type (ce35cd22d5)

### Dependencies
- [LPS-188038] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.33.
- [LPS-184016] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.11.

## 6.1.6 - 2023-06-15

### Commits
- [LPS-161202] Source formatting (b7197509a3)
- [LPS-161202] fix related docker gradleTest error (f7b089aebf)
- [LPS-161202] gradle-plugins-workspace: always check the settings for the
property (5b8fa13fe6)
- [LPS-161202] gradle-plugins-workspace: apply just to root project (0ac8d8e931)
- [LPS-161202] update testIntegration gradleTest (9f4ec226f9)
- [LPS-161202] apply propertiesPlugin for all projects (89579b59e6)
- [LPS-161202] improve logic (c14337ceeb)
- [LPS-161202] remove dulplicate codes (bcc986a86e)
- [LPS-161202] fix initBundle setFile error (19efc46d0b)
- [LPS-161202] no need to pass default bundle url (b0d310ed9c)
- [LPS-161202] fix productInfo gradleTest error (c1a65a1d05)
- [LPS-161202] remove propertiesTask configuration (84857535ae)
- [LPS-161202] support to correctly get correct productInfo (61849b9c04)
- [LPS-161202] fix gradleTest error (b797b6b378)
- [LPS-161202] add gradleTest (8d3ff58562)
- [LPS-161202] apply gradle properties plugin to rootProjectConfigurator
(7a2a6a65ed)

### Dependencies
- [LPS-185695] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.32.
- [LPS-186327] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.31.
- [LPS-186211] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.29.
- [LPS-166111] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.28.

## 6.1.5 - 2023-06-05

### Commits
- [LPS-182759] Create FunctionObjectEntryManager microservice client extension,
its configuration and factory (61ead0285a)

## 6.1.4 - 2023-05-31

### Dependencies
- [POSHI-543] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.27.
- [LPS-185460] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.26.
- [LPS-185020] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.25.

## 6.1.3 - 2023-05-19

### Commits
- [LPS-183262] Regen (53dc3bf35a)
- [LPS-183262] gradle-plugins-workspace: applies plugin (20484e8691)
- [LPS-183262] gradle-plugins-workspace: updates build.gradle (6eca7c6457)

### Dependencies
- [LPS-150272] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.24.
- [LPS-183262] Update the gradle-properties-plugin dependency to version 1.4.6.
- [LPS-182369] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.23.
- [LPS-184465] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.22.

## 6.1.2 - 2023-05-16

### Commits
- [LPS-147105] Inline (7d6fa4575e)
- [LPS-147105] Inline (f1a73f8d4d)
- [LPS-147105] Variable name (9046bd25ec)
- [LPS-147105] gradle-plugins-workspace: updates test cases to assert that the
unversioned tomcat directory is removed from the destination (37b186c741)
- [LPS-147105] gradle-plugins-workspace: updates distBundleZip test case
(79bd56e75b)
- [LPS-147105] gradle-plugins-workspace: updates initBundle test case
(8812099c32)
- [LPS-147105] gradle-plugins-workspace: uses copySpec.forEach on the files to
rename the paths, then clean up the unversioned tomcat directory (b28bb6052c)
- [LPS-181002] Type wrong, the original class package is wrong. SF will hint
"Class/package does not exist". (f552866dda)
- [LPS-181002] Update log4j xml. (aa553cfec8)

## 6.1.1 - 2023-05-09

### Commits
- [LPS-183973] gradle-plugins-workspace: switch dependency to the
createClientExtensionConfig task since that task now depends on assemble
(4aa9225d18)
- [LPS-183439] Rename (db06701c9c)

## 6.0.3 - 2023-05-06

### Commits
- [LPS-183588] Windows (14af93567f)
- [LPS-183588] Source formatting (6a81efad0d)
- [LPS-183588] Sort (97cc472379)
- [LPS-183588] Inline (d7699cf4e3)
- [LPS-183588] Inline (c40dce4d13)
- [LPS-183588] Move task creation (44aec0f22d)
- [LPS-183588] Move logic (cd70a47c9f)
- [LPS-183588] Variable name (94279347ed)
- [LPS-183588] Variable name (b5486527de)
- [LPS-183588] Variable name (9e31391492)
- [LPS-183588] Method name (6c70077fc3)
- [LPS-183588] Check task (f2c3a1ad87)
- [LPS-183588] gradle-plugins-workspace: uses constants in test (625047651e)
- [LPS-183588] gradle-plugins-workspace: change task name to be more generic in
case we want to do additional validations in the future (bf77ca64ec)
- [LPS-183588] gradle-plugins-workspace: if a workspace contains duplicate
client extension ids across multiple projects, fail the
createClientExtensionConfigTask task for those projects (b1c63f1b9a)
- [LPS-183588] gradle-plugins-workspace: adds test case (1b27cd6152)

### Dependencies
- [POSHI-528] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.21.

## 6.0.2 - 2023-05-03

### Commits
- [LPS-183286] regen (543308177b)
- [LPS-183286] gradle-plugins-workspace: fixes ClassCastException (637ccbd7e3)
- [LPS-183286] gradle-plugins-workspace: updates test case to ensure that empty
assembles are correctly handled (7e4f3bf7b2)
- [LPS-176097] update to upstream liferay/batch:latest image (e6cca68563)

## 6.0.1 - 2023-05-03

### Commits
- [LPS-182799] regen (80077e6e96)
- [LPS-182799] Logger (f53a7c9ad5)

## 5.1.0 - 2023-05-02

### Commits
- [LPS-183286] Baseline (86bd2921d0)
- [LPS-183286] Not needed (a98fdbd0f9)
- [LPS-183286] Rename variable (9635f7334f)
- [LPS-183286] Inline (6f77069dff)
- [LPS-183286] Rename variable (b1aef9479d)
- [LPS-183286] Move configuration logic (85f8950e22)
- [LPS-183286] Inline (da41f23f5d)
- [LPS-183286] Variable name (f969a4111b)
- [LPS-183286] Variable name (87c7ef226b)
- [LPS-183286] gradle-plugins-workspace: refactor: moves configuring tasks from
JSON node to private method (ca78e214e3)
- [LPS-183286] gradle-plugins-workspace: refactor: moves copySpec configuration
to private method to buy space (06f95ee958)
- [LPS-183286] gradle-plugins-workspace: moves check to private method
(6c7d83c07f)
- [LPS-183286] gradle-plugins-workspace: checks for active profile (fd5f53c0d3)
- [LPS-183286] gradle-plugins-workspace: not needed since this happens during
the base configure method (9bc71aafb1)

## 5.0.6 - 2023-05-02

### Commits
- [LPS-183286] gradle-plugins-workspace: baseline (ba6623ca62)
- [LPS-183286] gradle-plugins-workspace: apply to create client extension task -
only add client extensions that match the active profile property (8af980e908)
- [LPS-183286] gradle-plugins-workspace: directly configure the
assembleClientExtensionTask with the only the seleted profile (8b2da8754d)
- [LPS-183286] gradle-plugins-workspace: lazily configure the deploy tasks so we
can reliably set the profile property during the configure phase (961b4ed59a)
- [LPS-183286] gradle-plugins-workspace: applies Node plugin if any scripts are
found in the package.json file (23cbfc56d8)
- [LPS-183286] gradle-plugins-workspace: updates test case to assert that only
one yaml file runs the assemble fromTask (6000e83faa)
- [LPS-183167] Consistency (386a11656e)
- [LPS-183167] gradle-plugins-workspace: prevents reading malformed CX yaml file
names (139bfca6aa)
- [LPS-183167] gradle-plugins-workspace: skips the yaml file if it uses default
(dabeeb1e98)
- [LPS-183167] gradle-plugins-workspace: adds the base and profile CX yaml files
as inputs on each assemble task (7c8d64bdde)
- [LPS-183167] gradle-plugins-workspace: adds each CX yaml file as an input on
the create CX config task (a4f587f5f4)

## 5.0.5 - 2023-04-27

### Commits
- [LPS-182799] Sort (c165892041)
- [LPS-182799] Save space (047ee42e63)
- [LPS-182799] Lambdas disables the cache (f380c9b6a4)
- [LPS-182799] Logger (50c1bee9d5)
- [LPS-182799] gradle-plugins-workspace: excludes directories from project
evaluation if they match one of the exclude patterns (d666191211)
- [LPS-182799] gradle-plugins-workspace: updates test case to assert that the
excluded patterns are not added as projects (ccdd84f96c)

### Dependencies
- [LPS-182359] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.20.

## 5.0.4 - 2023-04-26

### Commits
- [LPS-179114] gradle-plugins-workspace: cleanup: inlines and simplifies the
logic for retrieving and interpolating template content (bd655e61f0)
- [LPS-179114] gradle-plugins-workspace: cleanup: consolidates writing to output
with template backup (d4e50b0dd5)
- [LPS-179114] gradle-plugins-workspace: uses getter functions when reading file
content (c2fec02d9a)
- [LPS-179114] gradle-plugins-workspace: adds annotated getter methods for input
files (01f59a31c7)
- [LPS-179114] gradle-plugins-workspace: simplify: makes the Project a field
(304daf3af9)
- [LPS-181454] Wordsmith (b236de821c)
- [LPS-181454] Register the client extension in the Workspace (b0e4586289)

### Dependencies
- [LPS-182065] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.19.
- [LPS-182410] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.18.
- [LPS-181216] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.17.

## 5.0.3 - 2023-04-20

### Commits
- [LPS-182153] gradle-plugins-workspace: uses taskprovider to defer the fromTask
task query until after the other tasks have been evaluated (9576e63a71)
- [LPS-182153] gradle-plugins-workspace: reproduces the issue with a test case
(a8d262dcd8)

### Dependencies
- [POSHI-522] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.16.

## 5.0.2 - 2023-04-20

### Commits
- [LPS-182253] gradle-plugins-workspace: unrelated: fix test failure
(54b585c614)
- [LPS-182253] gradle-plugins-workspace: updates test case to assert that stars
do not expand for non-frontend CX types (1c6a4aaea3)
- [LPS-182253] gradle-plugins-workspace: only expand globs on frontend
classified CX entries (e2331d255f)

### Dependencies
- [LPS-154671] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.15.

## 5.0.1 - 2023-04-19

### Commits
- [LPS-181738] Revert "LPS-181738 Error logged from UnicodeProperties when
deploying client extensions that contain array properties" (9914e268cf)
- [LPS-180905] Fix tests (2a2c2c7fd1)
- [LPS-180905] Wordsmith (0b328b630d)

### Dependencies
- [LPS-179838] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.14.

## 5.0.0 - 2023-04-18

### Commits
- [LPS-180905] Sort/rename/wordsmith (d58dd5886e)

## 4.3.10 - 2023-04-18

### Commits
- [LPS-180905] gradle-plugins-workspace: baseline (2de7387ad4)
- [LPS-180905] gradle-plugins-workspace: cleanup: inlines configurers
(c54195c93e)
- [LPS-180905] gradle-plugins-workspace: cleanup: removes unused runtime block
(a860ba23c1)
- [LPS-181331] gradle-plugins-workspace: forces preditable ordering on the
expanded paths so that the test can make reliable assertions (74ae5bfc39)
- [LPS-181331] gradle-plugins-workspace: adds test case for glob expansion
(8d4f58a166)
- [LPS-181331] gradle-plugins-workspace: expands using globs instead of regex
(cb9d1227c0)
- [LPS-180500] gradle-plugins-workspace: prevents infinite recursion whenever
the destination directory is in a sub-directory of the from statement
(6a4502058a)
- [LPS-180905] gradle-plugins-workspace: adds test case to assert on the
behavior of the override client extension yaml file (d6a23ba4dc)
- [LPS-180905] gradle-plugins-workspace: expands the patterns in-memory before
writing the config file (60289c2139)
- [LPS-180905] gradle-plugins-workspace: fixes task ordering so that wildcard
expansion will always happen after the output files have been assembled
(04067f8113)
- [LPS-180905] gradle-plugins-workspace: allows overriding the assemble property
in override yaml files (af5ec7c710)
- [LPS-180905] gradle-plugins-workspace: ensure that the assemble task runs
before creating the client extension config (f6ea6fcc70)
- [LPS-180905] gradle-plugins-workspace: dynamically reads and creates deploy
tasks when override yaml files are found (6eb52d85d8)

## 4.3.9 - 2023-04-17

### Commits
- [LPS-181738] Error logged from UnicodeProperties when deploying client
extensions that contain array properties (786c8a239a)
- [LPS-181303] Update workspace to support instance settings client extension
(f0282efb48)

## 4.3.8 - 2023-04-17

### Commits
- [LPS-179095] Fix clean (d1088927d8)

### Dependencies
- [POSHI-517] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.10.
- [POSHI-517] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.13.

## 4.3.7 - 2023-04-13

### Commits
- [LPS-179095] gradle-plugins-workspace: relativize the project dir paths
instead of changing the glob pattern (7d3f078b8b)

### Dependencies
- [LPS-181208] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.12.
- [LPS-178903] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.11.

## 4.3.6 - 2023-04-13

### Commits
- [LPS-177393] Fix dependency version conflict issue (d636c04d07)

### Dependencies
- [LPS-177393] Update the jackson-dataformat-yaml dependency to version 2.10.3.
- [LPS-150272] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.10.

## 4.3.5 - 2023-04-12

### Commits
- [LPS-181335] gradle-plugins-workspace: ignores non-Map values (2e4f4a8055)

## 4.3.3 - 2023-04-11

### Commits
- [LPS-181200] [workspace] allow properties to be set in client-extension.yaml
and add a couple of new utility properties (403fdb7258)
- [LPS-181195] [workspace] set the force policy for OSGi configurations by
default (delete on uninstall bundle, update config on bundle update)
(636db3f8c3)

### Dependencies
- [POSHI-503] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.9.
- [LPS-180505] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.8.

## 4.3.2 - 2023-04-11

### Commits
- [LPS-147105] add gradleTest (2e6fec524b)
- [LPS-147105] copy tomcat configuration for initBundle and distBundle
(dfb83093aa)
- [LPS-181266] auto update gradle.plugins.workspace (e40c3200b5)

## 4.3.1 - 2023-04-11

### Commits
- [LPS-181118] manage virtualInstanceId as property that can be directly
specified (be49dbe616)

### Dependencies
- [LPS-180402] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.7.

## 4.2.2 - 2023-04-05

### Commits
- [LPS-179095] Baseline (a0e598a5da)
- [LPS-179095] Tests (2568cb3505)
- [LPS-179095] Copy projects (0972296f36)
- [LPS-179095] All tasks (4277190638)
- [LPS-179095] Windows (e39a47bb16)
- [LPS-179095] Logging (c4736c0b71)
- [LPS-179095] gradle-plugins-workspace: removes unused code from
ModulesProjectConfigurator (72d4cf8805)
- [LPS-179095] gradle-plugins-workspace: for each project, disable all tasks if
the project file path matches one the exclude patterns (2b04737daa)
- [LPS-179095] gradle-plugins-workspace: adds new extension property to define
exclude globs in build.gradle files (3a339b916f)
- [LPS-179095] gradle-plugins-workspace: adds test cases (2834e0c366)

## 4.2.1 - 2023-04-04

### Commits
- [LRCI-3496] Variable names (a7f3e1c9f2)
- [LRCI-3496] set autoRemove to true and then we can avoid depending on
removeDockerContainer (20f6be608e)
- [LPS-123192] Fix for windows (3f44f803d7)

### Dependencies
- [LPS-174140] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.6.

## 4.2.0 - 2023-04-04

### Commits
- [LPS-180500] sdk: autogenerated: updateFileVersions (3ca429f691)

## 4.1.32 - 2023-04-04

### Commits
- [LPS-180500] gradle-plugins-workspace: autogenerated: baseline (92db378af3)
- [LPS-180500] gradle-plugins-workspace: updates test cases to reflect the new
build output (b5a55d0916)
- [LPS-180622] gradle-plugins-workspace: uses the right copy paths (c08e8bcf0d)
- [LPS-180500] gradle-plugins-workspace: uses a unique namespaced output folder
name and automatically excludes it from assemble copies (f82ee69913)

### Dependencies
- [LPS-180091] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.5.
- [LPS-179775] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.4.

## 4.1.31 - 2023-03-28

### Commits
- [LPS-179132] Add to workspace (07b68fae69)

### Dependencies
- [LPS-172794 LPS-179816] Update the com.liferay.gradle.plugins.source.formatter
dependency to version 5.2.3.

## 4.1.30 - 2023-03-27

### Commits
- [LPS-179794] Comments (8dfb554524)

## 4.1.29 - 2023-03-27

### Commits
- [LPS-179794] Sort (2de2be0f73)
- [LCD-24326] align workspace terms with documentation (82f426cecc)
- [LPS-179796] Define an instance configuration client extension which allows
setting Liferay Virtual Instance settings (b0ad6af828)
- [LPS-179794] Allow batch client extensions to have any name (4cde38821a)

### Dependencies
- [LPS-178596] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.2.

## 4.1.28 - 2023-03-22

### Commits
- [LPS-179303] Revert "LPS-179303 Rename oAuthApplicationUserAgent to
oAuthResourceServer and oAuthApplicationHeadlessServer to
oAuthClientCredentials" (fbdbcf1bf9)

## 4.1.27 - 2023-03-22

### Commits
- [LPS-179303] Rename oAuthApplicationUserAgent to oAuthResourceServer and
oAuthApplicationHeadlessServer to oAuthClientCredentials (beea130463)

### Dependencies
- [LPS-177812] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.1.

## 4.1.26 - 2023-03-20

### Commits
- [LPS-178925] use the new pattern in workspace plugin (a0a11ec2d2)

## 4.1.25 - 2023-03-16

### Commits
- [LPS-172374] -gradle-plugins-workspace-include-defaults (1424cef7c7)

### Dependencies
- [LPS-177352] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.2.0.

## 4.1.24 - 2023-03-15

### Commits
- [LPS-177378] always add docker tasks to client-extension projects (7a11753949)

## 4.1.23 - 2023-03-13

### Commits
- [LPS-173583] improve logic (893f30e9fd)
- [LPS-173583] add exclude module dir support for workspace plugin (236816d55a)

## 4.1.22 - 2023-03-07

### Commits
- [LPS-177378] Rename from to fromTask (dbcf5eb526)

### Dependencies
- [LPS-176090] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.1.181.

## 4.1.21 - 2023-03-05

### Commits
- [LPS-177026] add buildDockerImage and cleanDockerImage to client-extension
projects (d84bd850fb)

## 4.1.20 - 2023-03-05

### Commits
- [LPS-158124] Don't need to run prune every time after build (2ff212daf3)
- [LPS-158124] Add DockerListImage Task (a1344fd869)
- [LPS-158124] Add withDangling property for pruneCmd (f1ce3b514e)
- [LPS-158124] Add gradleTest pruneDockerImage (673966c49c)
- [LPS-158124] Add new gradle task 'pruneDockerImage' (fcd1169368)

## 4.1.19 - 2023-03-03

### Commits
- [LPS-177378] check if the from in assemble block is a taskName (399709e981)

## 4.1.18 - 2023-03-01

### Commits
- [LPS-177027] add staticContent support in gradle-plugins-workspace
(f6f5c759c6)

## 4.1.17 - 2023-02-23

### Commits
- [LPS-175968] Wordsmith (1cc91d33d0)
- [LPS-175968] batch engine client extension support (ba766e0738)

### Dependencies
- [LPS-175159] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.1.180.
- [LPS-175159] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.1.179.

## 4.1.16 - 2023-02-20

### Commits
- [LPS-176175] Add siteInitializer to client-extension.properties (d86c80bbd8)
- [LPS-151626] Let's get rid of separation between sample minimal and sample
default (93be1e8441)

## 4.1.15 - 2023-02-16

### Commits
- [LPS-175300] Copy samples (3094a37d33)

### Dependencies
- [POSHI-486] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.9.

## 4.1.14 - 2023-02-15

### Commits
- [LPS-175300] remove default assets configuration and add node build to all
(273a7c0387)

### Dependencies
- [LPS-166481] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.1.178.

## 4.1.13 - 2023-02-10

### Commits
- [LPS-175188] copy client extensions to liferayHome/osgi/client-extensions
(d6a08a998f)

### Dependencies
- [POSHI-471] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.1.177.

## 4.1.12 - 2023-02-09

### Commits
- [LPS-174535] Apply SF (9654581cc6)

### Dependencies
- [POSHI-474] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.8.
- [LPS-171941] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.1.176.
- [LPS-174535] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.1.175.

## 4.1.11 - 2023-02-05

### Commits
- [LPS-173869] fix default integration plugin different tomcat version issue
(3bac24aecb)
- [LPS-173869] copy test moudle dependon initBundle (b7f64811e8)

### Dependencies
- [LPS-172305] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.1.174.

## 4.1.10 - 2023-01-27

### Dependencies
- [POSHI-261] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.7.

## 4.1.9 - 2023-01-27

### Commits
- [LPS-172916] Remove immediate from gradle-plugins-workspace module
(8575aa48d7)

### Dependencies
- [POSHI-469 LPS-173718] Update the com.liferay.gradle.plugins.target.platform
dependency to version 3.0.6.
- [LPS-173718] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.1.173.

## 4.1.8 - 2023-01-25

### Commits
- [LPS-172978] Update Workspace tests (fbb00239c6)

### Dependencies
- [POSHI-261] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.5.
- [LPS-172813] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.1.172.

## 4.1.7 - 2023-01-17

### Commits
- [LPS-172781] Update (bafa16e70a)
- [LPS-172781] add source formatter plugin to root of workspace (91beeb3679)

### Dependencies
- [LPS-172781] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.1.171.
- [LPS-172781] Update the com.liferay.gradle.plugins.source.formatter dependency
to version 5.1.170.

## 4.1.6 - 2023-01-12

### Commits
- [LPS-172665] correctly set the default homePageURL for oAuthApplication types
(d544514af3)

## 4.1.5 - 2022-12-21

### Commits
- [LPS-168892] Only oAuthApplicationHeadlessServer and oAuthApplicationUserAgent
have homePageURL property (5dd228565e)

## 4.1.4 - 2022-12-21

### Commits
- [LPS-169846] fix angular sample client-extension build (e282344d85)

## 4.1.2 - 2022-12-16

### Commits
- [LPS-166479] themeSpritemap uses assets folder (23a82af6c5)

## 4.1.1 - 2022-12-16

### Commits
- [LPS-166479] add themeSpritemap support to workspace task (84f7baf3e3)

## 4.0.32 - 2022-12-13

### Commits
- [LPS-167694] Add a new task to Liferay workspace plugin for running Upgrade SF
checks (fc306f2c67)
- [LPS-160184] restore springContext gradleTest (5282d9983a)

## 4.0.30 - 2022-11-29

### Commits
- [LPS-168980] fix gradleTest issue (51f3cfb10d)

## 4.0.28 - 2022-11-28

### Commits
- [LPS-169543] Rename to match usage in
modules/sdk/gradle-plugins-workspace/src/main/java/com/liferay/gradle/plugins/workspace/internal/client/extension/NodeBuildConfigurer.java
(356449369a)
- [LPS-169543] As used (d351b73c14)
- [LPS-169543] Wrong name (e49509690c)
- [LPS-168901] forgot to readd themeFavicon (18b258e5b1)
- [LPS-169543] add support for properties field in client-extension.yaml mapping
(1ce32d7e55)

## 4.0.27 - 2022-11-24

### Commits
- [LPS-168901] allow node builds for all CSS or JS related client extension
(9c175174f0)

## 4.0.26 - 2022-11-23

### Commits
- [LPS-169432] Inline (6af3754124)
- [LPS-169432] Rename (6bf2241d23)
- [LPS-169432] Add support for two new types, notificationType && workflowAction
(5b1c068577)
- [LPS-169432] refactor to easier mechanism for changes (21813dd720)
- [LPS-160184] fix workspace osgiExt gradleTest issue (b7056d6fe0)

## 4.0.24 - 2022-11-15

### Commits
- [LPS-167335] fix designPack and springContext gradleTest issue (7de81ec6da)
- [LPS-167335] fix workspace gradleTest error (ecd5491c91)
- [LPS-167335] fix verifyBundleBad MD5 test case (2b2e1593ac)
- [LPS-167335] fix gradleTest error for gradle workspace plugin (386680a852)

### Dependencies
- [POSHI-438] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.4.
- [POSHI-438] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.3.

## 4.0.23 - 2022-11-11

### Commits
- [LPS-164101] Replace CookieKeys method calls with CookiesManagerUtil ones with
the exact same functionality. (65ff38891b)

### Dependencies
- [POSHI-432] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.2.

## 4.0.22 - 2022-10-31

### Dependencies
- [POSHI-426 LPS-167024] Update the com.liferay.gradle.plugins.target.platform
dependency to version 3.0.1.

## 4.0.21 - 2022-10-28

### Commits
- [LCD-14678] small Dockerfile fix (5d0fbaad8b)

## 4.0.20 - 2022-10-27

### Commits
- [LCD-14678] improve support for frontend and job client extension projects
(cbf004d7ff)

## 4.0.19 - 2022-10-20

### Commits
- [LPS-163589] - Update dependencies and tsconfig (3f4a1d2558)

## 4.0.17 - 2022-09-15

### Commits
- [LPS-162929] Fix typos in RootProjectConfigurator and improve exception
message (5650a76d26)
- [LPS-150857] Update README.markdown (77ff7e244f)
- [LPS-150857] Update plugins Gradle version (4c389b37ce)

## 4.0.12 - 2022-08-12

### Commits
- [LPS-159495] Wordsmith (20888af7ee)
- [LPS-159495] Distinguish java.lang.String from java.net.URL (5914ebdf27)
- [LPS-159495] Wordsmith (04cd195f4d)
- [LPS-159495] upgrade download gradle plugin version and improve logic
(d0bff6683f)

### Dependencies
- [LPS-159495] Update the gradle-download-task dependency to version 5.0.0.

## 4.0.11 - 2022-08-09

### Commits
- [LCD-14591] copy all files from src/**/* to static/ (3e6f6e0a60)

## 4.0.10 - 2022-07-26

### Commits
- [LPS-154811] Workspace complains of not having Gson (7dd011536e)

## 4.0.9 - 2022-07-25

### Commits
- [LPS-154811] - manually update gson to version 2.9.0 (db17a37e07)

## 4.0.8 - 2022-07-18

### Commits
- [LPS-154072] Source formatting (49e77059f8)
- [LPS-154072] tagImage need to dependon buildImage (1ddb3142d0)
- [LPS-154072] Fix bug (115206ec8d)
- [LPS-154072] simplify code (11e3b7439e)
- [LPS-154072] improve logic (e76d48d039)
- [LPS-154072] improve (6afe3d4154)
- [LPS-154072] Add gradleTest for docker local registry (52262f43ad)
- [LPS-154072] Add local registry support for createDockerfile (050c80a236)
- [LPS-154072] Set default value of docker pull policy as true (ec200ad982)
- [LPS-154072] Add username and password properties (ac3bcf86c9)
- [LPS-154072] Improve logic (73e68baac5)
- [LPS-154072] Add docker local registry support (47d553b3d9)

### Dependencies
- [LPS-154072] Update the gradle-docker-plugin dependency to version 6.7.0.

## 4.0.7 - 2022-07-15

### Commits
- [LPS-51081] Apply (52ec5f96e8)

### Dependencies
- [LPS-51081] Update the com.liferay.gradle.plugins.target.platform dependency
to version 3.0.0.

## 4.0.6 - 2022-07-11

### Commits
- [LPS-155204] Move dockerDeploy task to docker category (dcde9adbcb)

## 4.0.5 - 2022-07-08

### Commits
- [LPS-154340] apply rest and service plugins for correct project (b27359e87e)

## 4.0.4 - 2022-07-08

### Commits
- [LPS-156068] revert createDockerfileAll task (da360ae6ad)
- [LPS-153667] support to exclude some special gradleTest case for gradle
plugins (9f6658c287)

## 4.0.3 - 2022-07-05

### Commits
- [LCD-14464] Source formatting (a0856543e3)
- [LCD-14464] rename (0c5201b10a)
- [LCD-14464] make sure ids in LCP.json are safe, i.e. alpha-numeric-lowercase
(1c7868eb3b)

## 4.0.2 - 2022-06-30

### Commits
- [LPS-157179] use closure (5e40fdce7c)
- [LPS-157179] set input for CreateClientExtensionConfig task (79d775657e)

### Dependencies
- [POSHI-362] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.46.

## 4.0.1 - 2022-06-28

### Dependencies
- [POSHI-373] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.45.

## 3.5.3 - 2022-06-20

### Commits
- [LCD-14295] Apply (9c859534e0)
- [LCD-14295] Move (799f85a7bd)
- [LCD-14295] Apply (1cec9a391c)
- [LCD-14295] Move (f8a5b0dd4e)

## 3.5.2 - 2022-06-20

### Commits
- [LCD-14295] Rename (2d1ff8bc66)
- [LCD-14295] correct substitutions (bccba218be)
- [LCD-14295] Source formatting (ea906c4f55)
- [LCD-14295] should not add client-extension.yaml in root dir of workspace
(aadafaaed6)
- [LCD-14295] update default Dockerfile for static resources (8c17e535b0)
- [LCD-14295] encapsulate config generation to separate task to make outputs
register with zip provider (7eaa0db5f9)
- [LPS-150378] SF, inline (fa2af49346)

## 3.5.1 - 2022-06-16

### Commits
- [LCD-14295] Wordsmith (2075c4a48a)
- [LCD-14295] update tests (d70e83af04)
- [LCD-14295] dont set version for ThemeCSSTypeConfigurer (c385571f05)
- [LCD-14295] Move (f196ef8140)

## 3.5.0 - 2022-06-16

### Commits
- [LCD-14295] Sort (1fca1bb980)
- [LCD-14295] don't set a version that appends it to zip file. (e90f83df82)
- [LCD-14295] use correct dockerhub repository (6733d8b1bb)
- [LCD-14295] Source formatting (98b006c99c)

### Dependencies
- [LCD-14295] Update the com.liferay.petra.lang dependency to version 5.1.2.
- [LCD-14295] Update the com.liferay.petra.string dependency to version 5.2.0.

## 3.4.49 - 2022-06-16

### Commits
- [LCD-14295] semver (10527c4006)
- [LCD-14295] add functional test (35f83edd7f)
- [LCD-14295] remove unneeded DesignPack configurator and fix test (302161da98)
- [LCD-14295] add ClientExtensionProjectConfigurator that supports many
client-extension types (658fb52733)

### Dependencies
- [LCD-14295] Update the jackson-dataformat-yaml dependency to version 2.13.3.
- [LCD-14295] Update the com.liferay.petra.string dependency to version 5.2.0.
- [LCD-14295] Update the com.liferay.petra.lang dependency to version 5.1.2.

## 3.4.48 - 2022-06-07

### Commits
- [LPS-154070] Sort (068da77734)
- [LPS-154070] Source formatting (c9275504ef)
- [LPS-154070] create all docker file for environments (5ba12031e1)

## 3.4.47 - 2022-06-07

### Commits
- [LPS-155204] Organize docker tasks under Docker Tasks group (eed8740016)

## 3.4.45 - 2022-05-31

### Commits
- [LPS-149188] Source formatting (7f40ae4c0d)
- [LPS-149188] add finalized task for gradleTest (24fccf0fd9)
- [LPS-149188] Stop docker container after finish gradle tests (847b142828)
- [LPS-149188] Avoid docker port allocated (aec28cf89d)
- [LPS-149188] Fix gradleTest failure in provideModules (b6d541ad03)
- [LPS-149188] Add source-formatter-suppressions.xml file (cf7f37cf2c)
- [LPS-149188] Add portal kernal dependecy for foo-test (b296c328a1)
- [LPS-149188] Set environment as other for startDockerContainerWithScripts
(93e6b07de8)
- [LPS-149188] Shold throw GradleException in VerifyProductTask (ba1d38aa10)
- [LPS-149188] gralde-plugin-workspace gradleTest case faiure need to fix
(bab73424be)

## 3.4.44 - 2022-05-31

### Commits
- [LPS-150769] Source formatting (398805efc4)
- [LPS-150769] set connection timeout (a10283ecdd)
- [LPS-150769] add another productinfo backup url (bb16ef2fc9)

## 3.4.43 - 2022-05-31

### Commits
- [LPS-125578] Only show workspace product key warning once (0a2d939d06)

## 3.4.42 - 2022-05-24

### Dependencies
- [POSHI-353] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.44.

## 3.4.41 - 2022-05-17

### Commits
- [LPS-152789] perp next (22b35ac090)
- [LPS-152789] Source formatting (c39865572e)
- [LPS-152789] Add gradleTest for checking spring context (86f1c802fd)

### Dependencies
- [LPS-152789] Update the com.liferay.ant.bnd dependency to version 3.2.9.

## 3.4.40 - 2022-05-11

### Commits
- [LPS-150378] SF, inline (15a4ebdc1f)

## 3.4.39 - 2022-04-26

### Commits
- [LPS-151349] Source formatting (46459d389a)
- [LPS-151349] configure liferay extension so deploy works correctly
(a4d671fb99)
- [LPS-151349] support devDependency and dependency in package.json (59059ccad6)
- [LPS-151349] rename to buildDesignPack and ensure it works with deploy and
distBundle tasks (9a7a718c62)
- [LPS-151349] use dependencies from npm (9415243b60)
- [LPS-151349] not needed (fa20a0809d)
- [LPS-151349] the base configurator already provides this (53f172e0e1)
- [LPS-151349] should not be copying to that location (140baa65c7)
- [LPS-151349] already configured by ThemeBuilder/CSSBuilder plugins
(81d7deb9b1)
- [LPS-151349] use gradle task to build design pack (db07519713)
- [LPS-151340] add gradleTest (454c5b4c0a)
- [LPS-151349] As an LXC developer, I want to build my own design packs
(1d3c7619dc)

### Dependencies
- [LPS-151349] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.43.

## 3.4.38 - 2022-04-19

### Commits
- [LPS-151675] Revert "LPS-151675 Add lxc directory to ignore list" (88356c652d)

## 3.4.37 - 2022-04-18

### Commits
- [LPS-151675] Add lxc directory to ignore list (63286e75c1)

## 3.4.36 - 2022-04-04

### Commits
- [LPS-149555] Source formatting (3d09b92909)
- [LPS-149555] ignore system/hidden directories (273c68668c)
- [LPS-149555] remove unused code (b511e1ca12)
- [LPS-149555] fix gradleTest error (ead8ce6717)
- [LPS-149555] fix DistBundleZip error and add gradleTest to avoid dulplicate
issue (47cf555244)

## 3.4.35 - 2022-04-04

### Commits
- [LPS-150312] update docker dependencies to support m1 mac (6b7c9d9eb9)

### Dependencies
- [LPS-150312] Update the docker-java dependency to version 3.2.8.
- [LPS-150312] Update the jna dependency to version 5.8.0.

## 3.4.34 - 2022-04-03

### Dependencies
- [POSHI-324] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.42.

## 3.4.33 - 2022-03-29

### Dependencies
- [LPS-77359 LPS-147656] Update the com.liferay.gradle.plugins.target.platform
dependency to version 2.1.41.

## 3.4.32 - 2022-03-28

### Dependencies
- [LPS-149478] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.40.

## 3.4.31 - 2022-03-23

### Commits
- [LPS-124014] rename (9af9a97e98)
- [LPS-124014] Source formatting (9a6b100294)
- [LPS-124014] Having a copy of .product_info.json inside of liferay gradle
workspace plugin (5a45783a6a)

## 3.4.28 - 2022-03-16

### Commits
- [LPS-147672] Source formatting (78c1f29fce)
- [LPS-147672] fix failing gradleTests related to node version (f5e263c702)
- [LPS-147672] add gradleTest for distBundle*All (d06bbd5c39)
- [LPS-147672] add task distBundleTarAll distBundleZipAll (2dbff9d0b3)
- [LPS-147672] append environment and timestamp to distBundle file (1257ceeacf)

## 3.4.27 - 2022-02-22

### Commits
- [POSHI-251] Use com.liferay.gradle.plugins.poshi.runner:3.0.27 (cf3a2de470)

## 3.4.26 - 2022-02-17

### Commits
- [LPS-147563] Check parent projects (8ddf51fa89)
- [LPS-147563] JSCompile should also depend on yarnInstall (2246d509a9)
- [LPS-147563] Add YarnPlugin to Workspace (7493f05309)

## 3.4.24 - 2022-02-03

### Commits
- [LPS-145268] Wordsmith (49b3374de8)
- [LPS-145268] Source formatting (82009d385d)
- [LPS-145268] remove default portal bundle url for InitBundleTask (06431997e9)

## 3.4.23 - 2022-01-12

### Commits
- [LPS-105380] prep nex (797e97c019)

### Dependencies
- [POSHI-232] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.39.

## 3.4.22 - 2021-12-16

### Commits
- [LPS-143544] Change node package manager to yarn (b0c1130e49)

## 3.4.21 - 2021-12-14

### Dependencies
- [LPS-143577] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.38.

## 3.4.19 - 2021-11-22

### Commits
- [LPS-142100] [LPS-141250] revert (eee87baefd)

## 3.4.17 - 2021-11-15

### Commits
- [LPS-142340] Support target platform verson end with u1 (c2b61ec639)
- [LPS-142398] - Update liferay-font-awesome to 3.5.0 (961becb5ed)
- [LPS-105380] Use version 1.4.0 (a3c60a486e)
- [LPS-137358] Remove unneeded dependencies (ae57f93369)

## 3.4.16 - 2021-10-19

### Dependencies
- [LPS-140947] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.37.
- [LPS-140947] Update the com.liferay.ant.bnd dependency to version 3.2.8.

## 3.4.15 - 2021-10-07

### Commits
- [LPS-136872] Fixed the impact of LPS-136872 on other former gradleTests
(96be8fe844)
- [LPS-138982] Update `liferay-font-awesome` to 3.4.2 (2e7db218b9)
- [LPS-136872] Add gradle test (6174f26926)
- [LPS-105380] Remove final from parameters (a4e6ca0985)
- [LPS-105380] Remove final from variables (5a4e0b26b4)
- [LPS-105380] Revert "LPS-105380 Remove final from variables" (cdda76fc8e)
- [LPS-105380] Revert "LPS-105380 Remove final from parameters" (b2f9eed2c2)
- [LPS-105380] Remove final from parameters (f615ff77ff)
- [LPS-105380] Remove final from variables (ffe6186e71)

## 3.4.14 - 2021-08-11

### Commits
- [LPS-136872] Rename for consistency (971708b268)
- [LPS-136872] disable verify task when use a customized bundle url (9a8d7543db)
- [LPS-136872] As a workspace developer, I expect Liferay to provide md5
checksums for its own bundles (221fdf4446)
- [LPS-105380] Merge consecutive if-statements (1ec70acda6)

## 3.4.13 - 2021-07-06

### Commits
- [LPS-134301] Sort (903441863d)
- [LPS-134301] Tests should use port 8888 to avoid conflicts (93988214ca)
- [LPS-134301] Call cleanDockerImage (6488860f54)
- [LPS-134301] Use simplified docker tags (84a71810dd)
- [LPS-134301] Fix existing Ext test case (19c057efab)
- [LPS-134571] Move deps from bintray to s3 (5132aeba33)

### Dependencies
- [LPS-135076] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.36.

## 3.4.12 - 2021-06-11

### Dependencies
- [LPS-133987] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.35.
- [LPS-133987] Update the com.liferay.ant.bnd dependency to version 3.2.7.

## 3.4.11 - 2021-06-03

### Commits
- [LPS-132042] rename (f7b0ae2656)
- [LPS-132042] Could not get property during war-core-ext build (04e5faf73a)

## 3.4.10 - 2021-06-01

### Dependencies
- [LPS-132730] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.34.
- [LPS-132252] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.33.

## 3.4.9 - 2021-05-03

### Commits
- [LPS-131439] sort and rename (aaed42fcd8)
- [LPS-131439] force docker gradle plugin to download new version image
(88430cdb05)
- [LPS-130505] reverted, republish (a1551030f2)
- [LPS-130505] Revert "LPS-130505 SF, no need to call methods" (69c643075f)
- [LPS-130505] SF, no need to call methods (879c7f4801)
- [LPS-123192] Fix test (feb36753c5)

## 3.4.8 - 2021-03-31

### Commits
- [LPS-129956] Automatically apply RESTBuilderPlugin to java projects
(432711a79e)

## 3.4.7 - 2021-03-30

### Dependencies
- [LPS-129874] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.32.

## 3.4.6 - 2021-03-24

### Commits
- [LPS-129696] Automatically apply service builder plugin to all osgi projects
(7b289ea5e6)

## 3.4.5 - 2021-03-17

### Commits
- [LPS-129267] Update default metal-cli version (f42daa8d6f)
- [LPS-105380] Partial revert (aafd3818ae)

### Dependencies
- [LPS-129267] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.31.

## 3.4.4 - 2021-03-10

### Commits
- [LPS-128853] Add non cdn url as backup (690c1950d5)

### Dependencies
- [LPS-128853] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.30.

## 3.4.3 - 2021-02-19

### Commits
- [LPS-128116] Sublime sort (8f9e0ec597)
- [LPS-128116] make sure that default docker image/container id are docker safe
(2caf25b814)
- [LPS-126919] make sures configs dir exists in build/docker even if it is empty
(c005912dcb)
- [LPS-105380] Auto SF, XMLDTDVersionCheck (a7cb83d66b)

## 3.4.2 - 2021-02-12

### Commits
- [LPS-124343] make bundle.checksum.md5 an opt-in explicit setting (29d7b0eea6)
- [LPS-125998] Remove it from sdk. SPI never worked since 7.0, so it should be
safe to remove these from Gradle sdks. (be83d09f77)
- [LPS-105380] Make var final (12e010029a)

### Dependencies
- [LPS-127801] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.29.

## 3.4.1 - 2021-01-12

### Commits
- [LPS-125632] Sort (106f710ccd)
- [LPS-125632] don't overwrite dockerImageId and dockerContainerId if they have
already been set (c8837bfa56)
- [LPS-125580] use Set for the field type (f16bf9c441)
- [LPS-125580] Preserve order for configurators (5ff5c783ad)

## 3.4.0 - 2020-12-30

### Dependencies
- [LPS-124343] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.28.

## 3.3.3 - 2020-12-30

### Commits
- [LPS-124343] fix tests (5dbd19f540)
- [LPS-124343] make bundleChecksumMD5 backward compatible (454a41286d)
- [LPS-124343] assert that verifyBundle fails when bad md5sum is given
(5415d4fb91)
- [LPS-124343] assert the existence of MD5 file (be0de24207)
- [LPS-124343] lets instead just use the liferay.workspace.bundle.url that is
set globally (2e778be501)
- [LPS-124343] rename task (d76085903c)
- [LPS-124343] move to gradle.properties file like other tests (0ad0b4b806)
- [LPS-124343] add gradle test (211f4c72dd)
- [LPS-124343] configure verify task via afterEvaluate so we can use taskOutputs
(658456263c)
- [LPS-124343] fixed setter name (e63b5f3543)
- [LPS-124343] Use bundle checksum data from product_infos to verify bundle
downloads (6db20e0fa5)
- [LPS-105380] Inline (e47639f436)
- [LPS-123871] rename (dba6a5d7e7)

## 3.3.2 - 2020-12-02

### Commits
- [LPS-123871] feel free to rename this, but the nesting was too deep
(ad79802d17)
- [LPS-123871] fix the filename extension of marketplace/override (4d3085f653)

## 3.3.1 - 2020-12-01

### Commits
- [LPS-123937] sort (cebd4efa82)
- [LPS-123937] Failed to run createDockerContainer task due to invalid volume
specification on Windows (424c71923b)

## 3.2.1 - 2020-11-30

### Commits
- [LPS-123871] Baseline (4a2406ba16)
- [LPS-123871] Whitespace (7874bfed5f)
- [LPS-123871] fix test (246f4731b6)
- [LPS-123871] copy modules_ext jar into each deployed docker config
osgi/marketplace/override folder (350c68104f)

## 3.2.0 - 2020-11-25

### Dependencies
- [LPS-123192] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.27.

## 3.1.1 - 2020-11-25

### Commits
- [LPS-123192] Backward compatibility (tests) (8e3c12bffe)
- [LPS-123192] Backward compatibility (5cb1401f3c)

## 3.1.0 - 2020-11-16

### Dependencies
- [LPS-123192 LPS-82091] Update the com.liferay.gradle.plugins.target.platform
dependency to version 2.1.26.

## 3.0.17 - 2020-11-16

### Commits
- [LPS-123192] Fix tests (a0769d730f)
- [LPS-123192] Backward compatibility (tests) (f16b34fa77)
- [LPS-123192] Backward compatibility (d6076deb36)

## 3.0.16 - 2020-11-13

### Commits
- [LPS-122967] use afterEvaluate and avoid using concrete type (f9c16beb11)
- [LPS-122967] configure processResources only if buildCSS exists (3fbf5e0b99)
- [LPS-122967] remove problematic eclipse build/gradleTest/src/groovy folder
(de7af5d29a)
- [LPS-122967] configure processResources task to process scss and sass-cache
files (ff759d231d)

## 3.0.15 - 2020-11-04

### Commits
- [LPS-122958] Source formatting (a57da45918)
- [LPS-122958] allow developers to append instructions to dockerfile via a file
(fdb013e43d)
- [LPS-122958] upgrade gradle-docker-plugin to 6.6.1 (b22f5164b7)
- [LPS-111291] Fix tests (01ce02be78)

### Dependencies
- [LPS-122958] Update the gradle-docker-plugin dependency to version 6.6.1.

## 3.0.14 - 2020-11-02

### Commits
- [LPS-111291] Import statements (d414bad0fa)
- [LPS-111291] Update readme (a87d2593e9)
- [LPS-111291] Gradle 5.6.4 tests (40f4f9e2f3)
- [LPS-111291] Update README.markdown (eea12b7f8f)
- [LPS-111291] Update plugins Gradle version (003c3832b0)

### Dependencies
- [LPS-111291] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.25.

## 3.0.13 - 2020-10-27

### Commits
- [LPS-121824] Update workspace and project templates for 7.4 (7cf8361f15)

## 3.0.12 - 2020-10-08

### Commits
- [POSHI-115] Update com.liferay.gradle.plugins.workspace to 3.0.12 (aaa56944bc)
- [POSHI-115] Update com.liferay.gradle.plugins.poshi.runner to 3.0.6
(a3bf78c6e7)

## 3.0.10 - 2020-10-01

### Commits
- [LPS-105380] Methods don't need to be static (c42da838cc)

## 3.0.9 - 2020-09-25

### Commits
- [LPS-105280] Auto SF, inline (37eb8a5bf4)

## 3.0.8 - 2020-08-21

### Commits
- [LPS-108380] SF, use String.valueOf (7ca7e14c1f)

## 3.0.7 - 2020-08-19

### Commits
- [LPS-119389] root project also needs to be configured to not use npm
(6cedd42dbf)

## 3.0.6 - 2020-08-18

### Commits
- [LPS-118868] Add Test (e1eef89458)
- [LPS-118868] Add EP versions to TargetPlatform (0fbdf87c00)

## 3.0.5 - 2020-08-15

### Commits
- [LPS-118918] Update test (102c1f9465)
- [LPS-118918] Exclude themes from included with Portlet type projects
(6a78c09465)
- [LPS-118918] Remove portlet requirement (58ef0ee916)
- [LPS-118918] Add Test (b7abb65924)

## 3.0.4 - 2020-08-12

### Commits
- [LPS-118936] setUpYarn should format package.json before writing it to file
(08b5fe4cc5)
- [LPS-105380] Move variable inside if statement (4ba0e61ee5)
- [LRDOCS-8120] ant format-javadoc (001fa4addd)

## 3.0.3 - 2020-07-30

### Commits
- [LPS-118113] Update constants in gradle-plugins (2ef9ab88dd)

## 3.0.0 - 2020-07-21

### Dependencies
- [LPS-105873] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.24.

## 2.6.2 - 2020-07-21

### Commits
- [LPS-105873] Remove line break (ba1cc49b74)
- [LPS-105873] fix 7.0 gulp themes when using yarn (4f2973dda3)
- [LPS-105873] yarn gradleTest (498512a309)
- [LPS-105873] Apply (6539d8d782)
- [LPS-117314] Directory name (82b18d8385)
- [LPS-117314] disable cleanCopyLibs task as it wipes required local /lib jar
(ac86c35a80)
- [LPS-117314] fix gradleTest pluginClasspathDir isolation (95fd67e6d4)

## 2.6.1 - 2020-07-15

### Commits
- [LPS-105873] wordsmith (e6b228aed8)

## 2.5.9 - 2020-07-15

### Commits
- [LPS-105873] Sort (e6b6ef4d9c)
- [LPS-105873] Rename method (4b6a4ffd37)
- [LPS-105873] Inline (cba0abec2e)
- [LPS-105873] allow workspace to work with yarn (af1a056aaa)

### Dependencies
- [LPS-116282] Update the com.liferay.ant.bnd dependency to version 3.2.6.

## 2.5.8 - 2020-07-06

### Dependencies
- [LPS-105873] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.23.

## 2.5.7 - 2020-06-26

### Dependencies
- [LPS-115015 LPS-88645] Update the com.liferay.gradle.plugins.target.platform
dependency to version 2.1.22.

## 2.5.6 - 2020-06-24

### Dependencies
- [LPS-88645 LPS-116049 LPS-116041] Update the
com.liferay.gradle.plugins.target.platform dependency to version 2.1.21.

## 2.5.5 - 2020-06-21

### Commits
- [LPS-88645] BundleExtension (89c6e73a9d)

### Dependencies
- [LPS-115431 LPS-115714 LPS-88645] Update the
com.liferay.gradle.plugins.target.platform dependency to version 2.1.20.

## 2.5.4 - 2020-06-18

### Commits
- [LPS-111700] Rename directory (98f1039ffe)
- [LPS-88645] Fix compile (cd7da9704a)

### Dependencies
- [LPS-88645] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.19.

## 2.5.3 - 2020-06-17

### Commits
- [LPS-111700] Sort (3f4a6d9245)
- [LPS-111700] set download command use quiet mode (363bf448b5)
- [LPS-111700] use codec library (cf5a806780)

## 2.5.1 - 2020-06-11

### Dependencies
- [LPS-114565 LPS-105380 LPS-98022] Update the
com.liferay.gradle.plugins.target.platform dependency to version 2.1.18.

## 2.5.0 - 2020-06-10

### Commits
- [LPS-111461] Remove comments (76aa38bf9c)
- [LPS-111461] Remove line break (e6375a21ee)
- [LPS-111461] As used (182695c0a6)
- [LPS-111461] Variable name (832ae517d7)
- [LPS-111461] Method name (5a70ee0d4b)
- [LPS-111461] Remove line break (5dafecd3d8)
- [LPS-111461] ensure exactly correct classpath for gradle-util (d3a07183a7)
- [LPS-111461] add wildcard test (5deb08b447)
- [LPS-111461] support wildcard * (e230a9d78d)
- [LPS-111461] add tests (611ab14850)

## 2.4.13 - 2020-06-10

### Commits
- [LPS-111461] add flexible project layout to modules/wars/themes (ea0ab5277c)
- [LPS-111460] remove unneeded test since createToken is deprecated (9d5c2cd355)
- [LPS-114882] Source formatting (81e9001972)
- [LPS-114882] Add gradleTest for providedModules configuration in Target
Platform (ff18e0bf25)
- [LPS-113024] [LPS-114570] update because of CI CDN issues (8a163df22c)

## 2.4.12 - 2020-06-04

### Dependencies
- [LPS-114882] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.17.

## 2.4.11 - 2020-06-03

### Commits
- [LPS-114168] Inline (68e4892b3d)
- [LPS-114168] Avoid chaining (0c54e755b3)
- [LPS-114168] Add warning if user is override liferay.workspace.product key
default value (60c22ca7b8)

## 2.4.9 - 2020-06-02

### Dependencies
- [LPS-114705] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.16.

## 2.4.8 - 2020-06-01

### Dependencies
- [LPS-88645] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.15.

## 2.4.6 - 2020-05-28

### Dependencies
- [LPS-88645] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.14.

## 2.4.5 - 2020-05-26

### Dependencies
- [LPS-88645] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.13.

## 2.4.4 - 2020-05-21

### Dependencies
- [LPS-113914 LPS-105380 LPS-88645] Update the
com.liferay.gradle.plugins.target.platform dependency to version 2.1.12.

## 2.4.3 - 2020-05-21

### Commits
- [LPS-111460] Rename variable (0fd70ca273)
- [LPS-111460] Logging (3c55d2c403)
- [LPS-111460] assert log warning (5a14c11f29)
- [LPS-111460] switch to non-deprecated API (6d5a711962)
- [LPS-111460] remove all bodys of methods that will never be used and add
warning to taskAction (2cedf3694c)
- [LPS-111460] deprecated createTokenTask since token is no longer being used
(9910dd4ed9)

## 2.4.2 - 2020-05-20

### Dependencies
- [LPS-88645] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.11.

## 2.4.1 - 2020-05-18

### Dependencies
- [LPS-88645] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.10.

## 2.4.0 - 2020-05-18

### Commits
- [LPS-111700] remove unnecessary newline (1f0b9d92a0)

## 2.3.7 - 2020-05-18

### Commits
- [LPS-111700] Sort alphabetically (aed26967ee)
- [LPS-111700] Rename variables (e403310494)
- [LPS-111700] Match property name (c6eb392771)
- [LPS-111700] fix tests (7fdbea6cf5)
- [LPS-111700] rename method (816d9d600d)
- [LPS-111700] use Optionals and HashMap for handling projectInfo (31533806a8)
- [LPS-111700] reename variables (03c547a22f)
- [LPS-111700] make private, no need for public access (066600867b)
- [LPS-111700] clean up tests (79a7221cea)
- [LPS-111700] rename tests (96b273198a)
- [LPS-111700] sort (98f4be809f)
- [LPS-111700] set a single property to control docker/bundle/target platform
(4e857a3d0f)

## 2.3.6 - 2020-05-13

### Commits
- [LPS-105380] revert (c22fc8b3e7)

### Dependencies
- [LPS-105380 LPS-88645 LPS-113624] Update the
com.liferay.gradle.plugins.target.platform dependency to version 2.1.9.

## 2.3.5 - 2020-05-07

### Dependencies
- [LPS-113180] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.8.

## 2.3.4 - 2020-05-06

### Dependencies
- [LPS-88645] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.7.

## 2.3.3 - 2020-05-04

### Dependencies
- [LPS-112922] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.6.

## 2.3.2 - 2020-04-28

### Dependencies
- [LPS-110422] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.5.

## 2.3.1 - 2020-04-22

### Dependencies
- [LPS-98417] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.4.

## 2.3.0 - 2020-04-20

### Commits
- [LPS-112052] rename (72924e306b)

## 2.2.13 - 2020-04-20

### Commits
- [LPS-112052] Source formatting (5e9eafa56b)
- [LPS-112052] Avoid using def keyword (26585f4e93)
- [LPS-112052] Move task creation logic (6e6dc9019c)
- [LPS-112052] Use existing method (e8778187e3)
- [LPS-112052] Remove System.out (99c99248b3)
- [LPS-112052] fix gradleTest, update to gulp 4 (e3c7dceb0c)
- [LPS-112052] gradleTest (466e946506)
- [LPS-112052] set app.server.tomcat.version automatically (d95c6d6745)

## 2.2.12 - 2020-04-20

### Commits
- [LPS-107812] Line breaks (d49949953c)
- [LPS-107812] Add readme to gradle-plugins-workspace (280c887ef5)

### Dependencies
- [LPS-110835] Update the com.liferay.ant.bnd dependency to version 3.2.5.
- [LPS-110835] Update the com.liferay.ant.bnd dependency to version 3.2.4.

## 2.2.11 - 2020-03-18

### Commits
- [LPS-110131] Remove unneeded line break (1f7a681389)
- [LPS-110131] Rename file to .gitkeep (34ac9538ef)
- [LPS-110131] specify docker image (44aa20f557)
- [LPS-110131] add new test for asserting correct patching behavior (e41df06c73)
- [LPS-110131] fix existing tests (cc62e6713c)

## 2.2.10 - 2020-03-17

### Commits
- [LPS-110131] add patching folder support (015dff0467)
- [LPS-109820] Use BndUtil (70e7a0c61d)

## 2.2.9 - 2020-03-09

### Commits
- [LPS-110051] Remove unneeded initBundle task configuration (5396bf2197)
- [LPS-107612] Revert "LPS-107612 Update liferay-npm-scripts v21.0.0"
(ee9abf03ff)
- [LPS-109787] Fix gradle tests (36bbc3e9e7)
- [LPS-109787] fix other test (ecbdbdbfc5)
- [LPS-109787] rename to be more descriptive (bacb0263d4)
- [LPS-109787] Configurations from specific environments should be copied after
common configurations (df5b40d55f)
- [LPS-109787] Add test case to verify local configuration scripts will override
common scripts (a464b6692f)

## 2.2.8 - 2020-03-04

### Dependencies
- [LPS-106149] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.3.

## 2.2.7 - 2020-02-25

### Dependencies
- [LPS-109312] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.2.

## 2.2.6 - 2020-02-20

### Commits
- [LPS-107862] Remove unneeded line break (40f02b83f2)
- [LPS-107862] Add missing parentheses (9622947741)
- [LPS-107862] Explicitly define the type (9fec024d90)
- [LPS-107862] Remove unneeded line break (502e8c9b9a)
- [LPS-107862] only mount the deploy folder instead of all work dir (c118252060)
- [LPS-107862] fix container ids and logs (735352db0d)
- [LPS-107862] test multiple script and assert that "other" is executed and not
"local" (6b3d9e52fd)
- [LPS-107862] assert we don't have errors with 'cp' command in scripts
(df6c79e7cf)
- [LPS-107862] rename (fe26d64a2f)
- [LPS-107862] whitespace (cbafa2a18c)
- [LPS-107862] move closer to where used (9af735cff2)
- [LPS-107862] we don't need these files (0fd3bf6160)
- [LPS-107862] Clean up tests (ee3a317cfc)
- [LPS-107862] Implement requested changes (4cb4faeb57)
- [LPS-107862] Remove unnecessary change (e8f9860abc)
- [LPS-107862] Update tests (10adb8c421)
- [LPS-107862] Add Tests (7cb4459a17)
- [LPS-107862] simplify (4115795991)
- [LPS-107862] rename var (0ab7050bb7)
- [LPS-107862] Refactor to simplify code (1b8eddd213)
- [LPS-107862] Only copy over script files if they exist (2e39e59887)
- [LPS-105380] Manually spotted and fixed (19bc5e04c0)

### Dependencies
- [LPS-107155] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.1.

## 2.2.5 - 2020-02-10

### Commits
- [LPS-104679] SF for ValidatorIsNullCheck (5c0afcf6fb)

## 2.2.4 - 2020-01-28

### Commits
- [LPS-107519] Sort (4a211932c9)
- [LPS-107519] Rename method (623fab24d2)
- [LPS-107519] Remove unneeded static keyword (825499c491)
- [LPS-107519] refactor into method (e20f0c7958)
- [LPS-107519] also create .touch file in scripts folder (46d25f9569)
- [LPS-107612] Update liferay-npm-scripts v21.0.0 (6195578317)
- [LPS-105380] Rename exception variables (b3173da81b)
- [LPS-107202] Update to liferay-npm-scripts v19.0.1 (f7704c187c)

### Dependencies
- [LPS-107155] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.1.0.

## 2.2.3 - 2020-01-10

### Commits
- [LPS-105889] not needed (792e8ef018)
- [LPS-105380] Do not use initials for *Exception parameters (84d97070ed)
- [LPS-102243] Partial revert (b5ee7e9c16)
- [LPS-102243] Remove portal-test-integration and all references to it from
build and classpath (c2efa6c7f2)
- [LPS-106167] Update build.gradle (06136ec832)
- [LPS-106167] Use com.liferay.petra.string.StringPool instead (9aa4d72e67)

### Dependencies
- [LPS-105889] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.0.8.

## 2.2.2 - 2019-12-24

### Dependencies
- [LPS-105502] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.0.7.

### Description
- Updated target platform dependency which switches to native Gradle5 BOM
support

## 2.2.1 - 2019-12-13

### Commits
- [LPS-104646] Apply WSDDBuilder and UpgradeTableBuilder plugins to service
builder projects in workspace (480c676309)

## 2.2.0 - 2019-12-10

### Commits
- [LPS-103937] Fix typo (a6fe420977)
- [LPS-103937] Logging message (41a1927cc9)
- [LPS-103937] More descriptive variable name (9dff3cbe4d)
- [LPS-103937] Readability (no logic changes) (4dabb4c9f7)
- [LPS-103937] Update method name (9161c84991)
- [LPS-103937] Update variable name for code consistency (32a0298b55)
- [LPS-103937] Make project the first parameter for code consistency
(874dce4d20)
- [LPS-103937] Follow class name (641ed180a4)
- [LPS-103937] Remove unneeded static keyword (2586c97cd9)
- [LPS-103937] More descriptive variable name (9d91b719d8)
- [LPS-103937] Sort alphabetically (55d2d683fe)
- [LPS-103937] these tests should work (a607c2da7c)

## 2.1.15 - 2019-12-10

### Commits
- [LPS-103937] semver (9efd17fdf3)
- [LPS-103937] update tests (27fd76e257)
- [LPS-103937] normalize task name (cea69e9b5f)
- [LPS-103937] only configure task if they exist (a7bd6fd725)
- [LPS-103937] throw build exception if we don't have at least one config folder
other than common and docker (6e782baaa2)
- [LPS-104354] remove circular dependency on :initBundle and :plugins-sdk:war
(60bb83782d)
- [LPS-103937] add missing import (fda7b5952c)
- [LPS-103937] always add scripts folder to image so it can be modified by the
pre-configure script (3f2eb5f142)
- [LPS-103937] use /mnt/liferay as mount point instead (a368186fde)
- [LPS-101463] setUpTestableTomcat should depend on InitBundle if
liferayWorkspace.homeDir doesns't exist (eafc934dda)
- [LPS-103937] we don't need the scripts folder in Dockerfile, we can create it
in setup.sh (c917cd2813)
- [LPS-104355] configure deployFast task for workspace bundles and docker
containers (bccd549754)
- [LPS-104354] initBundle should also add deployable files to bundle after
unzipping (18702d422f)
- [LPS-103937] Use ${LIFERAY_MOUNT_DIR}/scripts to better integrate with
DXPCloud (c4ee2c32ed)
- [LPS-103937] Better default is user doesn't supply a root project version
(4b4974e593)
- [LPS-103937] enable remote java debugging for created containers (0790b00000)
- [LPS-103937] configure logsDockerContainer task to use configurable id
(cdb6feb1e5)
- [LPS-103937] cleanup eclipse warnings (d99237781b)
- [LPS-103937] only buildDockerImage task needs to depend on deploy task since
createDockerContainer depends on buildDockerImage now (d0ab524f7b)
- [LPS-103937] improve startDockerContainer task (027b4a605e)
- [LPS-103937] improve dockerCreateContainer task (0df1e2d3c7)
- [LPS-103937] improve removeDockerContainer task to use new workspaceExtension
field for container id (36645a9e70)
- [LPS-103937] improve stopDockerContainer task to use new configurable
container id (b44b34ed3c)
- [LPS-103937] buildDockerImage task improvements (3c48ce221f)
- [LPS-103937] adopt new script extension points when creating child images from
base liferay (15c6d2128b)
- [LPS-103937] refactor :dockerDeploy to copy all the configs into docker output
folder (0195b75cd9)
- [LPS-103937] reorganize adding docker related tasks in single method
(ad888f9509)
- [LPS-103937] add new sensible defaults docker image id and container name
which also can be overridden by users (844cdb2eed)
- [LPS-99147] update default docker image to latest 7.2.0-ga1 (66f8d6e60f)
- [LPS-104679] SF for LiteralStringEqualsCheck (7e5f27749c)

## 2.1.14 - 2019-11-27

### Commits
- [LPS-100515] Source formatting (eff42efa8d)
- [LPS-100515] refactor gradle tests to not directly invoke task.execute()
(47e957043f)
- [LPS-100515] explicitly add directory to clean/Delete task (73d0e8866a)
- [LPS-100515] Add assertion (e91857a335)
- [LPS-100515] Skip Gradle docker tests (83037d2206)
- [LPS-100515] Update plugins Gradle version (448efac158)
- [LPS-100515] Enable Gradle docker tests (3f42343758)

### Dependencies
- [LPS-100515] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.0.6.

## 2.1.13 - 2019-11-15

### Commits
- [LPS-103466] Sort (5b5ff8dcca)
- [LPS-103809] preop next (3768e9a4b7)

### Dependencies
- [LPS-104540] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.0.5.

## 2.1.12 - 2019-10-31

### Commits
- [LPS-103169] Fix gradleTest (use com.liferay.css.builder 3.0.2) (31efe7542b)
- [LPS-103466] revert (e4f42e7a25)
- [LPS-103466] Revert "LPS-103466 Sort" (fffa7e7259)
- [LPS-103466] Sort (1cd1d09425)
- [LPS-94523] Fix Gradle tests (e8966a69fb)
- [LPS-94523] Bump up jackson dependencies to 2.9.10 or 2.9.10.1 (1f4f471bfc)

## 2.1.11 - 2019-10-21

### Commits
- [LPS-103051] temp rollback (9a1e210294)

### Dependencies
- [LPS-95938] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.0.4.

## 2.1.10 - 2019-10-03

### Commits
- [LPS-100153] deploy now calls dockerDeploy automatically (f3597e24a4)

### Dependencies
- [LPS-102700] Update the com.liferay.ant.bnd dependency to version 3.2.3.
- [LPS-101947] Update the com.liferay.ant.bnd dependency to version 3.2.2.

## 2.1.9 - 2019-09-23

### Dependencies
- [LPS-86806] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.0.3.

## 2.1.8 - 2019-09-18

### Commits
- [LPS-67565] Declare "config" mode for portlets using MVCPortlet config mode
(2bb8dd9a5c)
- [LPS-101089] Remove usages on private package classes. (b598761012)

### Dependencies
- [LPS-101450] Update the com.liferay.ant.bnd dependency to version 3.2.1.

## 2.1.7 - 2019-09-05

### Commits
- [LPS-101060] add test (72250d06f7)
- [LPS-101060] fix version pattern for suffixed qualifiers (2f84c0723f)

## 2.1.6 - 2019-08-30

### Commits
- [LPS-99983] Sort alphabetically (ea9439258c)
- [LPS-99983] Missing line break (f8b86f11d7)
- [LPS-99983] Remove println (cd5c484f16)
- [LPS-99983] simplify tests (2a0b7c19b4)
- [LPS-99983] these files should not be used for this test (8d736cefc3)
- [LPS-99983] Fix test logic (9fcbc0b7e5)
- [LPS-99983] Add Tests (0829d55a98)
- [LPS-99983] Use new property name for workspace (db3c9ed9c0)
- [LPS-100448] fix tests (e363c9e9b6)

## 2.1.5 - 2019-08-27

### Commits
- [LPS-100448] Auto-SF (0ff1cd4057)
- [LPS-100168] Fix gradleTest (55ca3f97c1)
- [LPS-99577] fix classpath problem in tests (13be6aecd6)

### Dependencies
- [LPS-100335] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.0.2.

## 2.1.4 - 2019-08-19

### Commits
- [LPS-99977] Update Gradle plugins (f125baba8a)
- [LPS-98879] [LPS-96095] auto SF for servlet-api (032b53ca5e)

## 2.1.3 - 2019-08-09

### Commits
- [LPS-99442] Remove extra period in the default repos property (cbe8ac7999)
- [LPS-98937] combine tests to simplify (dad8697b2b)
- [LPS-98937] fix jspc failures in tests (c1bbb72164)

### Dependencies
- [LPS-98937] Update the com.liferay.ant.bnd dependency to version 3.2.0.

## 2.1.2 - 2019-08-07

### Commits
- [LPS-99382] Remove unneeded line breaks (32c3c72d19)
- [LPS-99382] Simplify (39488bd7bc)
- [LPS-99382] Sort alphabetically (6f22018a6c)
- [LPS-99382] Inline (29990d24e9)
- [LPS-99382] Inline method with only one usage (3296aaa5e4)
- [LPS-99382] Use final when the variable is used inside a closure (7fd763aa6d)
- [LPS-99382] Update variable names (48f0b0919b)
- [LPS-99382] Use constant instead of hard coded value (f178d0585a)
- [LPS-99308] fix test (e6cbccc57e)
- [LPS-99308] Expand tests (1754cc04fa)
- [LPS-99308] Remove commented out test code (3b01dbf60b)
- [LPS-99308] Add Test (bff247d962)
- [LPS-99308] Apply Liferay Plugin to hybrid projects - refactor slightly
(c532a6d67d)
- [LPS-99308] Apply Liferay Plugin to hybrid projects (640b891939)
- [LPS-99382] add test case (12c0752c1c)
- [LPS-99382] use callable for configuring dockerDeploy task (b1d36c3f84)

## 2.1.0 - 2019-08-02

### Commits
- [LPS-93483] try 2.1.1? (9b15f81863)

## 2.0.9 - 2019-08-02

### Commits
- [LPS-93483] Initialize variable (ed60ae4396)
- [LPS-93483] Inline (32daa13d1a)
- [LPS-93483] Inline (7c35b58087)
- [LPS-93483] Consistency (see WorkspacePlugin) (4867b5a2ed)
- [LPS-93483] Remove unneeded whitespace (c53b923c1b)
- [LPS-93483] Sort alphabetically (f4271c0e3e)
- [LPS-93483] use configuration callable for docker (cc4ed8d101)
- [LPS-93483] avoid classpath issue (b1004f09a9)
- [LPS-93483] Test case (c7be2a5d2b)
- [LPS-93483] Add support for buliding frontend projects in modules folder
(3d0f0940dc)
- [LPS-98914] docker should respect liferay.workspace.environment (87d3430153)
- [LPS-98914] docker label and tag not being set (848c0018f9)
- [LPS-98877] [LPS-96095] auto SF for portlet-api (fc1fff6de9)

## 2.0.8 - 2019-07-31

### Commits
- [LPS-97550] Add line break (3cb35cf3e7)
- [LPS-97550] Sort alphabetically (d096805ba3)
- [LPS-97550] Use final when the variable is used inside a closure (434f5d2480)
- [LPS-97550] As used (380650db25)
- [LPS-97550] Consistency with other Gradle plugins (5afd8d6875)
- [LPS-97550] Add test case (c1b89c4138)
- [LPS-97550] copy all testIntegrationClasses into test-classes/integration
(517435934d)

## 2.0.7 - 2019-07-30

### Commits
- [LPS-98190] move MetatypePlugin back to ant-bnd to support maven as well
(8c822456cc)
- [LPS-98190] Sort alphabetically (d7d4b518a4)
- [LPS-98190] Whitespace (fd043a6230)
- [LPS-98190] add test case (a386de13e5)
- [LPS-98190] add Metatype annotation processing support back since it was
removed in Bnd 4.0 (e941393f88)
- [LPS-84119] Do not declare var (85dc5fdf91)

### Dependencies
- [LPS-98190] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.0.1.
- [LPS-98190] Update the com.liferay.ant.bnd dependency to version 3.1.0.

## 2.0.6 - 2019-07-03

### Commits
- [LPS-97601] Follow existing patterns (3da8ac438d)

## 2.0.5 - 2019-07-03

### Commits
- [LPS-97601] Source formatting (64430dd22d)
- [LPS-97601] fix classpath issues in tests (e8db32ee57)
- [LPS-97601] exclude problematic bnd version (6bb9f0fb78)
- [LPS-97601] jsp compiler is now more strict (671a71eeed)
- [LPS-96911] Add missing taglibs (9cc5c9e823)
- [LPS-96611] breaking test case (752c3f9b7f)

### Dependencies
- [LPS-97601] Update the com.liferay.gradle.plugins dependency to version
4.0.51.
- [LPS-97601] Update the com.liferay.gradle.plugins.poshi.runner dependency to
version 2.2.13.
- [LPS-97601] Update the com.liferay.gradle.plugins.theme.builder dependency to
version 2.0.7.
- [LPS-97601] Update the com.liferay.portal.tools.bundle.support dependency to
version 3.4.3.
- [LPS-97601] Update the com.liferay.gradle.plugins dependency to version
4.0.51.
- [LPS-97601] Update the com.liferay.gradle.plugins.poshi.runner dependency to
version 2.2.13.
- [LPS-97601] Update the com.liferay.gradle.plugins.theme.builder dependency to
version 2.0.7.
- [LPS-97601] Update the com.liferay.portal.tools.bundle.support dependency to
version 3.4.3.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
4.0.51.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
4.0.50.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
4.0.49.
- [LPS-93507] Update the com.liferay.gradle.plugins dependency to version
4.0.48.
- [LPS-95819] Update the com.liferay.gradle.plugins dependency to version
4.0.47.
- [LPS-95819] Update the com.liferay.gradle.plugins dependency to version
4.0.46.
- [RELEASE-1607] Update the com.liferay.gradle.plugins dependency to version
4.0.45.
- [LPS-96911] Update the com.liferay.gradle.plugins dependency to version
4.0.44.
- [LPS-95819] Update the com.liferay.gradle.plugins dependency to version
4.0.43.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
4.0.42.
- [LPS-96247] Update the com.liferay.gradle.plugins dependency to version
4.0.41.
- [LPS-97169] Update the com.liferay.gradle.plugins dependency to version
4.0.40.
- [LPS-95442] Update the com.liferay.gradle.plugins dependency to version
4.0.39.
- [LPS-96611] Update the com.liferay.gradle.plugins dependency to version
4.0.38.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
4.0.37.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
4.0.36.
- [LPS-95455] Update the com.liferay.gradle.plugins dependency to version
4.0.35.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
4.0.34.
- [LPS-96860] Update the com.liferay.gradle.plugins dependency to version
4.0.33.
- [LPS-96830] Update the com.liferay.gradle.plugins dependency to version
4.0.32.
- [LPS-95455] Update the com.liferay.gradle.plugins dependency to version
4.0.31.
- [LPS-95442] Update the com.liferay.gradle.plugins dependency to version
4.0.30.
- [LPS-93510] Update the com.liferay.gradle.plugins dependency to version
4.0.29.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
4.0.28.
- [LRCI-350] Update the com.liferay.gradle.plugins.poshi.runner dependency to
version 2.2.13.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
4.0.27.
- [LPS-96198] Update the com.liferay.gradle.plugins dependency to version
4.0.26.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
4.0.25.
- [LPS-95442 LPS-95819] Update the com.liferay.gradle.plugins dependency to
version 4.0.24.

## 2.0.4 - 2019-06-04

### Commits
- [LPS-96290] Fix test (a48b5cbd08)
- [LPS-96376] Update to liferay-npm-scripts v2.1.0 (prettier) (7930ab3625)
- [LPS-96290] Auto SF (see 6f5139c) (60691e7bca)
- [LPS-96290] fix tests (caebcf052f)
- [LPS-84119] Use 'osgi.core' instead of 'org.osgi.core' (01606b6fb1)
- [LPS-95388] Revert "LPS-95388 Temporarily disable tests for
gradle-plugins-theme-builder" (e7366abc0d)

### Dependencies
- [LPS-95079] Update the com.liferay.portal.tools.bundle.support dependency to
version 3.4.3.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
4.0.23.
- [LPS-96376] Update the com.liferay.gradle.plugins dependency to version
4.0.22.
- [LPS-96206] Update the com.liferay.gradle.plugins dependency to version
4.0.21.
- [LPS-96290] Update the com.liferay.gradle.plugins dependency to version
4.0.20.
- [LPS-70170] Update the com.liferay.gradle.plugins dependency to version
4.0.19.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
4.0.18.
- [LPS-96252] Update the com.liferay.gradle.plugins dependency to version
4.0.17.
- [LPS-96091] Update the com.liferay.gradle.plugins dependency to version
4.0.16.
- [LPS-96018] Update the com.liferay.gradle.plugins dependency to version
4.0.15.
- [LPS-95915] Update the com.liferay.gradle.plugins dependency to version
4.0.14.
- [LPS-94999] Update the com.liferay.gradle.plugins dependency to version
4.0.13.
- [LPS-94999] Update the com.liferay.gradle.plugins.theme.builder dependency to
version 2.0.7.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
4.0.12.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
4.0.11.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
4.0.10.
- [LPS-95635] Update the com.liferay.gradle.plugins dependency to version 4.0.9.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version 4.0.8.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version 4.0.7.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version 4.0.6.
- [LRCI-264] Update the com.liferay.gradle.plugins.poshi.runner dependency to
version 2.2.12.

## 2.0.3 - 2019-05-20

### Commits
- [LPS-95388] Temporarily disable tests for gradle-plugins-theme-builder
(e555e5c068)
- [LPS-95279] Fix whitespace (3bfa528f9e)
- [LPS-95279] Add line breaks because the order matters (866efb6211)
- [LPS-95279] fix gradle-plugin-workspace tests with latest TP changes
(7106aa2034)
- [LPS-95279] change the order that we apply these to fix up some version issues
(7af9a77f0b)
- [LPS-95279] more resolve task work (9a86b91941)
- [LPS-95279] refactor targetPlatformIDE extension, remove includedGroups and
add indexSources (9b296b8d83)
- [LPS-95279] automatically add third party coms to targetPlatformBoms
configuration (8a55c7ae17)

### Dependencies
- [LPS-95279] Update the com.liferay.gradle.plugins.target.platform dependency
to version 2.0.0.
- [LPS-95723] Update the com.liferay.gradle.plugins dependency to version 4.0.5.
- [LRCI-264] Update the com.liferay.gradle.plugins.poshi.runner dependency to
version 2.2.11.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version 4.0.4.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version 4.0.3.
- [LPS-95442] Update the com.liferay.gradle.plugins dependency to version 4.0.2.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version 4.0.1.
- [LPS-95442] Update the com.liferay.gradle.plugins dependency to version 4.0.0.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.198.
- [LPS-95413] Update the com.liferay.gradle.plugins dependency to version
3.13.197.

## 2.0.2 - 2019-05-13

### Dependencies
- [LPS-84119 LPS-91420] Update the com.liferay.gradle.plugins dependency to
version 3.13.196.
- [LPS-94947] Update the com.liferay.gradle.plugins.poshi.runner dependency to
version 2.2.10.
- [LPS-94948] Update the com.liferay.gradle.plugins dependency to version
3.13.195.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.194.
- [LPS-95330] Update the com.liferay.gradle.plugins dependency to version
3.13.193.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.192.
- [LPS-94948] Update the com.liferay.gradle.plugins dependency to version
3.13.191.
- [LPS-91241] Update the com.liferay.gradle.plugins dependency to version
3.13.190.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.189.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.188.
- [LPS-94947] Update the com.liferay.gradle.plugins dependency to version
3.13.187.
- [LPS-91967] Update the com.liferay.gradle.plugins dependency to version
3.13.186.
- [LPS-89415] Update the com.liferay.gradle.plugins dependency to version
3.13.185.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.184.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.183.
- [LPS-94764] Update the com.liferay.gradle.plugins dependency to version
3.13.182.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.181.
- [LPS-93505] Update the com.liferay.gradle.plugins dependency to version
3.13.180.
- [LPS-89415] Update the com.liferay.gradle.plugins dependency to version
3.13.179.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.178.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.177.
- [LPS-77425] Update the com.liferay.gradle.plugins dependency to version
3.13.176.

## 2.0.1 - 2019-04-24

### Commits
- [LPS-94606] inline (669d7da032)
- [LPS-94606] use sb as var name (369a34b2c8)
- [LPS-94606] Properly handle --provided-modules with multiple files
(4aed438c7a)

### Dependencies
- [LPS-94606] Update the com.liferay.gradle.plugins.target.platform dependency
to version 1.1.13.
- [LPS-93513] Update the com.liferay.gradle.plugins dependency to version
3.13.175.
- [LPS-94466] Update the com.liferay.gradle.plugins dependency to version
3.13.174.
- [LPS-94523] Update the com.liferay.gradle.plugins dependency to version
3.13.173.
- [LPS-88911] Update the com.liferay.gradle.plugins dependency to version
3.13.172.
- [LPS-89210] Update the com.liferay.gradle.plugins dependency to version
3.13.171.
- [LPS-94033] Update the com.liferay.gradle.plugins dependency to version
3.13.170.
- [LPS-89415] Update the com.liferay.gradle.plugins dependency to version
3.13.169.
- [LPS-93265] Update the com.liferay.gradle.plugins dependency to version
3.13.168.
- [LPS-86806] Update the com.liferay.gradle.plugins dependency to version
3.13.167.

### Description
- [LPS-94606] The `initBundle` task failed when the `providedModules`
configuration contained more than one dependency.

## 2.0.0 - 2019-04-15

### Commits
- [LPS-84119] Revert "LPS-84119 Use LocaleUtil instead of Locale when possible"
(648a6b5d96)
- [LPS-84119] Use LocaleUtil instead of Locale when possible (6ab0588891)
- [LPS-91967] Skip node_modules_cache directory (43a618c849)
- [LPS-91772] this is a publish of a new SF to revert the old one (23537d6e71)
- [LPS-91342] regen (334a31a0b6)

### Dependencies
- [LPS-93873] Update the com.liferay.gradle.plugins.target.platform dependency
to version 1.1.12.
- [LPS-91295] Update the com.liferay.gradle.plugins dependency to version
3.13.166.
- [LPS-93707] Update the com.liferay.gradle.plugins dependency to version
3.13.165.
- [LPS-91967] Update the com.liferay.gradle.plugins dependency to version
3.13.164.
- [LPS-93506] Update the com.liferay.gradle.plugins dependency to version
3.13.163.
- [LRDOCS-6412] Update the com.liferay.gradle.plugins dependency to version
3.13.162.
- [LPS-86806] Update the com.liferay.gradle.plugins dependency to version
3.13.161.
- [LPS-93350] Update the com.liferay.gradle.plugins dependency to version
3.13.160.
- [LPS-91222] Update the com.liferay.gradle.plugins dependency to version
3.13.159.
- [LRCI-65] Update the com.liferay.gradle.plugins.poshi.runner dependency to
version 2.2.9.
- [LPS-93471] Update the com.liferay.gradle.plugins dependency to version
3.13.158.
- [LPS-93258] Update the com.liferay.gradle.plugins dependency to version
3.13.157.
- [LPS-93124] Update the com.liferay.gradle.plugins dependency to version
3.13.156.
- [LPS-91772] Update the com.liferay.gradle.plugins dependency to version
3.13.155.
- [LPS-92677] Update the com.liferay.gradle.plugins dependency to version
3.13.154.
- [LPS-93045] Update the com.liferay.gradle.plugins dependency to version
3.13.153.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.152.
- [LPS-91967] Update the com.liferay.gradle.plugins dependency to version
3.13.151.
- [LPS-91420] Update the com.liferay.gradle.plugins dependency to version
3.13.150.
- [LPS-91772] Update the com.liferay.gradle.plugins dependency to version
3.13.149.
- [LPS-88911] Update the com.liferay.gradle.plugins dependency to version
3.13.148.
- [LPS-90402] Update the com.liferay.gradle.plugins dependency to version
3.13.147.
- [LPS-92746] Update the com.liferay.gradle.plugins dependency to version
3.13.146.
- [LPS-92746] Update the com.liferay.gradle.plugins dependency to version
3.13.145.
- [LPS-92568] Update the com.liferay.gradle.plugins dependency to version
3.13.144.
- [LPS-91420] Update the com.liferay.gradle.plugins dependency to version
3.13.143.
- [LPS-92223] Update the com.liferay.gradle.plugins dependency to version
3.13.142.
- [LPS-91549] Update the com.liferay.gradle.plugins dependency to version
3.13.141.
- [LPS-91342] Update the com.liferay.gradle.plugins dependency to version
3.13.140.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.139.

## 1.10.21 - 2019-03-21

### Commits
- [LPS-88784] Rename homeDir to destinationDir (d738a6d417)
- [LPS-88784] Revert "LPS-88784 Fix gradleTest" (28e0cb0c88)
- [LPS-88784] Fix gradleTest (9b65d51a74)
- [LPS-88784] Check if providedModules is empty (d1f74139a8)

### Dependencies
- [LPS-86806] Update the com.liferay.gradle.plugins dependency to version
3.13.138.
- [LPS-91967] Update the com.liferay.gradle.plugins dependency to version
3.13.137.
- [LPS-92311] Update the com.liferay.gradle.plugins dependency to version
3.13.136.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.135.
- [LRQA-47104] Update the com.liferay.gradle.plugins dependency to version
3.13.134.
- [LPS-91420] Update the com.liferay.gradle.plugins dependency to version
3.13.133.
- [LPS-91846] Update the com.liferay.gradle.plugins dependency to version
3.13.132.
- [LPS-91970] Update the com.liferay.gradle.plugins dependency to version
3.13.131.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.130.
- [LPS-85855] Update the com.liferay.gradle.plugins dependency to version
3.13.129.
- [LPS-91803] Update the com.liferay.gradle.plugins dependency to version
3.13.128.
- [LPS-91378 LPS-84119] Update the com.liferay.gradle.plugins dependency to
version 3.13.127.
- [LPS-89874] Update the com.liferay.gradle.plugins dependency to version
3.13.126.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.125.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.124.
- [LPS-89415] Update the com.liferay.gradle.plugins dependency to version
3.13.123.

## 1.10.20 - 2019-03-05

### Commits
- [LPS-88784] Sort alphabetically (1adb8e55e4)
- [LPS-88784] Inline variables (3161d96fc9)
- [LPS-88784] Add line break because these lines shouldn't be sorted
(83c9ebbd27)
- [LPS-88784] Sort alphabetically (885407c6d7)
- [LPS-88784] Avoid swallowing exception (7961498625)
- [LPS-88784] Remove System.out.println (a02c79847b)
- [LPS-88784] Remove unneeded method (1987a8c03c)
- [LPS-88784] use portal-tools-bundle-support for initBundle to consolidate
logic for gradle and maven (7989765c5b)

### Dependencies
- [LPS-91463] Update the com.liferay.gradle.plugins dependency to version
3.13.122.
- [LPS-91420] Update the com.liferay.gradle.plugins dependency to version
3.13.121.
- [LPS-86806] Update the com.liferay.gradle.plugins dependency to version
3.13.120.
- [LPS-89415] Update the com.liferay.gradle.plugins dependency to version
3.13.119.
- [LPS-91420] Update the com.liferay.gradle.plugins dependency to version
3.13.118.
- [LPS-89415] Update the com.liferay.gradle.plugins dependency to version
3.13.117.
- [LPS-91343] Update the com.liferay.gradle.plugins dependency to version
3.13.116.
- [LPS-89874] Update the com.liferay.gradle.plugins dependency to version
3.13.115.
- [LPS-91231] Update the com.liferay.gradle.plugins dependency to version
3.13.114.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.13.113.
- [LPS-89637] Update the com.liferay.gradle.plugins dependency to version
3.13.112.
- [LPS-89637] Update the com.liferay.gradle.plugins dependency to version
3.13.111.
- [LPS-90945] Update the com.liferay.gradle.plugins dependency to version
3.13.110.
- [LPS-86806] Update the com.liferay.gradle.plugins dependency to version
3.13.109.
- [LPS-89456] Update the com.liferay.gradle.plugins dependency to version
3.13.108.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.107.

## 1.10.19 - 2019-02-19

### Commits
- [LRQA-46662]/[LRQA-45313] Add compatibility version 7.2.0+ (ca1221e227)

### Dependencies
- [LRQA-46662] Update the com.liferay.gradle.plugins.target.platform dependency
to version 1.1.11.
- [LRQA-46662] Update the com.liferay.gradle.plugins.theme.builder dependency to
version 2.0.6.
- [LPS-90380] Update the com.liferay.gradle.plugins dependency to version
3.13.106.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.105.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.104.
- [LPS-90523] Update the com.liferay.gradle.plugins dependency to version
3.13.103.
- [LRDOCS-6300] Update the com.liferay.gradle.plugins dependency to version
3.13.102.
- [LPS-90378] Update the com.liferay.gradle.plugins dependency to version
3.13.101.
- [LPS-89456] Update the com.liferay.gradle.plugins dependency to version
3.13.100.
- [LPS-86853] Update the com.liferay.gradle.plugins dependency to version
3.13.99.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.98.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.97.
- [LPS-89456] Update the com.liferay.gradle.plugins dependency to version
3.13.96.
- [LPS-90204] Update the com.liferay.gradle.plugins dependency to version
3.13.95.

## 1.10.18 - 2019-02-11

### Commits
- [LPS-89518] update test urls to latest 7.1.2-ga3 (feac957ce2)
- [LPS-89518] Update to use 7.1 GA3 (a5b2ba472a)

### Dependencies
- [LPS-89518] Update the com.liferay.portal.tools.bundle.support dependency to
version 3.4.2.
- [LPS-89415] Update the com.liferay.gradle.plugins dependency to version
3.13.94.
- [LRQA-46630] Update the com.liferay.gradle.plugins dependency to version
3.13.93.
- [LPS-89415] Update the com.liferay.gradle.plugins dependency to version
3.13.92.
- [LPS-89415] Update the com.liferay.gradle.plugins dependency to version
3.13.91.
- [LPS-89415] Update the com.liferay.gradle.plugins dependency to version
3.13.90.
- [LPS-89874] Update the com.liferay.gradle.plugins dependency to version
3.13.89.
- [LPS-89415] Update the com.liferay.gradle.plugins dependency to version
3.13.88.
- [LPS-89916] Update the com.liferay.gradle.plugins dependency to version
3.13.87.
- [LPS-89415] Update the com.liferay.gradle.plugins dependency to version
3.13.86.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.13.85.
- [LPS-89274] Update the com.liferay.gradle.plugins dependency to version
3.13.84.
- [LPS-88851] Update the com.liferay.gradle.plugins dependency to version
3.13.83.
- [LPS-69035] Update the com.liferay.gradle.plugins dependency to version
3.13.82.
- [LPS-88665] Update the com.liferay.gradle.plugins dependency to version
3.13.81.
- [LPS-88665] Update the com.liferay.gradle.plugins dependency to version
3.13.80.
- [LPS-89415] Update the com.liferay.gradle.plugins dependency to version
3.13.79.
- [LPS-89415] Update the com.liferay.gradle.plugins dependency to version
3.13.78.
- [LPS-89567 LPS-89568] Update the com.liferay.gradle.plugins dependency to
version 3.13.77.
- [LPS-89415] Update the com.liferay.gradle.plugins dependency to version
3.13.76.
- [LPS-88665] Update the com.liferay.gradle.plugins dependency to version
3.13.75.
- [LPS-88665] Update the com.liferay.gradle.plugins dependency to version
3.13.74.
- [LPS-88665] Update the com.liferay.gradle.plugins dependency to version
3.13.73.
- [LPS-89415] Update the com.liferay.gradle.plugins dependency to version
3.13.72.
- [LPS-89415] Update the com.liferay.gradle.plugins dependency to version
3.13.71.
- [LPS-89369] Update the com.liferay.gradle.plugins dependency to version
3.13.70.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.13.69.
- [LPS-89445 LPS-89457] Update the com.liferay.gradle.plugins dependency to
version 3.13.68.
- [LPS-89415] Update the com.liferay.gradle.plugins dependency to version
3.13.67.
- [LPS-86806] Update the com.liferay.gradle.plugins dependency to version
3.13.66.
- [LPS-89388] Update the com.liferay.gradle.plugins dependency to version
3.13.65.
- [LPS-89415] Update the com.liferay.gradle.plugins dependency to version
3.13.64.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.63.
- [LPS-89228] Update the com.liferay.gradle.plugins dependency to version
3.13.62.
- [LPS-88909] Update the com.liferay.gradle.plugins dependency to version
3.13.61.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.60.

## 1.10.17 - 2019-01-15

### Dependencies
- [LPS-86705] Update the com.liferay.gradle.plugins.target.platform dependency
to version 1.1.10.
- [LPS-86705] Update the com.liferay.portal.tools.bundle.support dependency to
version 3.4.1.
- [LPS-89126] Update the com.liferay.gradle.plugins dependency to version
3.13.59.
- [LPS-86806] Update the com.liferay.gradle.plugins dependency to version
3.13.58.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.57.
- [LPS-87479] Update the com.liferay.gradle.plugins dependency to version
3.13.56.
- [LPS-88909] Update the com.liferay.gradle.plugins dependency to version
3.13.55.

## 1.10.16 - 2019-01-08

### Commits
- [LPS-83922] Ignore downloaded gradleTest files (4a0f4d084a)
- [LPS-83922] test unneeded (ec3df53e17)
- [LPS-85609] Simplify gradleTest (a8b0feff31)

### Dependencies
- [LPS-87469] Update the com.liferay.gradle.plugins dependency to version
3.13.54.
- [LPS-86705] Update the com.liferay.gradle.plugins.target.platform dependency
to version 1.1.9.
- [LPS-86705] Update the com.liferay.portal.tools.bundle.support dependency to
version 3.4.0.
- [LPS-88903] Update the com.liferay.gradle.plugins dependency to version
3.13.53.
- [LPS-88823] Update the com.liferay.gradle.plugins dependency to version
3.13.52.
- [LPS-87479] Update the com.liferay.gradle.plugins dependency to version
3.13.51.
- [LPS-41848] Update the com.liferay.gradle.plugins dependency to version
3.13.50.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.49.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.48.

## 1.10.15 - 2019-01-02

### Commits
- [LPS-88524] Fix clean (gw :sdk:gradle-plugins-workspace:clean) (280f1fc2d8)
- [LPS-88382] release temp revert (414be58373)

### Dependencies
- [LPS-86705] Update the com.liferay.gradle.plugins.target.platform dependency
to version 1.1.8.
- [LPS-86705] Update the com.liferay.portal.tools.bundle.support dependency to
version 3.3.0.
- [LPS-88170] Update the com.liferay.gradle.plugins dependency to version
3.13.47.
- [LPS-88382] Update the com.liferay.gradle.plugins dependency to version
3.13.46.
- [LPS-88382] Update the com.liferay.gradle.plugins dependency to version
3.13.45.
- [LPS-88382] Update the com.liferay.gradle.plugins dependency to version
3.13.44.
- [LPS-88552] Update the com.liferay.gradle.plugins dependency to version
3.13.43.
- [LPS-87590] Update the com.liferay.gradle.plugins dependency to version
3.13.42.
- [LPS-88181] Update the com.liferay.gradle.plugins dependency to version
3.13.41.
- [LPS-87488] Update the com.liferay.gradle.plugins dependency to version
3.13.40.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.39.
- [LPS-88183] Update the com.liferay.gradle.plugins dependency to version
3.13.38.
- [LPS-88171] Update the com.liferay.gradle.plugins dependency to version
3.13.37.
- [LPS-81706] Update the com.liferay.gradle.plugins dependency to version
3.13.36.
- [LPS-86806] Update the com.liferay.gradle.plugins dependency to version
3.13.35.
- [LPS-88186] Update the com.liferay.gradle.plugins dependency to version
3.13.34.
- [LPS-88223] Update the com.liferay.gradle.plugins dependency to version
3.13.33.
- [LPS-88186] Update the com.liferay.gradle.plugins dependency to version
3.13.32.

## 1.10.14 - 2018-12-04

### Commits
- [LPS-77897] Remove unused imports (e5c0dead36)
- [LPS-77897] Rename variable to portBindings (64d8a5152f)
- [LPS-77897] Fix bind path for Windows (1c2a87ffc4)
- [LPS-77897] ignore docker tests on CI (fda4a3d065)
- [LPS-77897] WarsProjectConfigurator already adds dockerDeploy task
(0103c35cad)
- [LPS-77897] add gradleTest for buildImage and createContainer (6801da46d1)
- [LPS-77897] the root project is not yet available (ddfd30efc3)
- [LPS-77897] Source formatting (9a6911c848)
- [LPS-77897] Sort alphabetically (3baf67c3b6)
- [LPS-77897] Update task names to match pattern in AppDockerPlugin (37edea9a18)
- [LPS-77897] Add checks for logging (307037ed97)
- [LPS-77897] Update method name for code readability (9535bdd8f0)
- [LPS-77897] Apply dockerDeploy task changes (00a4b82732)
- [LPS-77897] Rename the task to dockerDeploy since its copying files to
docker/deploy (d67b61393c)
- [LPS-77897] The deploy task is only used in WarsProjectConfigurator
(6726d1986a)
- [LPS-77897] Avoid hard coding the build directory (1f349f78cf)
- [LPS-77897] integrate docker with liferay-workspace (2c210d091a)

### Dependencies
- [LPS-77897] Update the com.liferay.gradle.plugins.target.platform dependency
to version 1.1.7.
- [LPS-77897] Update the gradle-docker-plugin dependency to version 3.6.2.
- [LPS-87471] Update the com.liferay.gradle.plugins dependency to version
3.13.31.
- [LPS-88171] Update the com.liferay.gradle.plugins dependency to version
3.13.30.
- [LPS-66010] Update the com.liferay.gradle.plugins dependency to version
3.13.29.
- [LPS-85828] Update the com.liferay.gradle.plugins dependency to version
3.13.28.
- [LPS-86406] Update the com.liferay.gradle.plugins dependency to version
3.13.27.

### Description
- [LPS-77897] Add support for Docker. The following tasks are now available:
	- `buildDockerImage`
	- `createDockerContainer`
	- `createDockerfile`
	- `dockerDeploy`
	- `logsDockerContainer`
	- `pullDockerImage`
	- `removeDockerContainer`
	- `startDockerContainer`
	- `stopDockerContainer`

## 1.10.13 - 2018-11-30

### Dependencies
- [LPS-87978] Update the com.liferay.gradle.plugins dependency to version
3.13.26.
- [LPS-87936] Update the com.liferay.gradle.plugins dependency to version
3.13.25.
- [LPS-87890] Update the com.liferay.gradle.plugins.poshi.runner dependency to
version 2.2.8.
- [LPS-86806 LPS-87890] Update the com.liferay.gradle.plugins dependency to
version 3.13.24.
- [LPS-87839] Update the com.liferay.gradle.plugins dependency to version
3.13.23.
- [LPS-86406] Update the com.liferay.gradle.plugins dependency to version
3.13.22.
- [LPS-87839] Update the com.liferay.gradle.plugins dependency to version
3.13.21.
- [LPS-87776] Update the com.liferay.gradle.plugins dependency to version
3.13.20.
- [LPS-86806] Update the com.liferay.gradle.plugins dependency to version
3.13.19.

## 1.10.12 - 2018-11-20

### Commits
- [LPS-87417] Update default Liferay Version to 7.1 GA2 (3d80901bca)
- [LPS-85609] Use Gradle 4.10.2 (9aa90f8961)

### Dependencies
- [LPS-87419] Update the com.liferay.gradle.plugins dependency to version
3.13.18.
- [LPS-87419] Update the com.liferay.gradle.plugins.target.platform dependency
to version 1.1.6.
- [LPS-87419] Update the com.liferay.portal.tools.bundle.support dependency to
version 3.2.7.
- [LPS-87503] Update the com.liferay.gradle.plugins dependency to version
3.13.17.

## 1.10.11 - 2018-11-19

### Dependencies
- [LPS-87466] Update the com.liferay.gradle.plugins dependency to version
3.13.16.
- [LPS-87466] Update the com.liferay.gradle.plugins.poshi.runner dependency to
version 2.2.7.
- [LPS-87466] Update the com.liferay.gradle.plugins.target.platform dependency
to version 1.1.5.

## 1.10.10 - 2018-11-16

### Commits
- [LPS-87192] Set the Eclipse task property gradleVersion (040b2abdee)
- [LPS-87192] Add variable gradleVersion (no logic changes) (2f7c0b2fe4)
- [LPS-85609] Fix for CI (test only 4.10.2) (4eed005731)
- [LPS-85609] Test plugins up to Gradle 4.10.2 (60905bc960)
- [LPS-85609] Fix for Gradle 4.0.2 (801a24514a)

### Dependencies
- [LPS-87371] Update the com.liferay.gradle.plugins dependency to version
3.13.15.
- [LPS-87466] Update the com.liferay.gradle.plugins dependency to version
3.13.14.
- [LPS-87466] Update the com.liferay.gradle.plugins.poshi.runner dependency to
version 2.2.6.
- [LPS-87466] Update the com.liferay.gradle.plugins.target.platform dependency
to version 1.1.4.
- [LPS-87366] Update the com.liferay.gradle.plugins dependency to version
3.13.13.
- [LPS-87293] Update the com.liferay.gradle.plugins dependency to version
3.13.12.
- [LPS-86916] Update the com.liferay.gradle.plugins dependency to version
3.13.11.
- [LPS-86916] Update the com.liferay.gradle.plugins dependency to version
3.13.10.
- [LPS-81704] Update the com.liferay.gradle.plugins dependency to version
3.13.9.

## 1.10.9 - 2018-10-29

### Dependencies
- [LPS-86549] Update the com.liferay.gradle.plugins dependency to version
3.13.8.
- [LPS-86583] Update the com.liferay.gradle.plugins dependency to version
3.13.7.
- [LPS-86581] Update the com.liferay.gradle.plugins dependency to version
3.13.6.
- [LPS-86581] Update the com.liferay.gradle.plugins.theme.builder dependency to
version 2.0.5.
- [LPS-86576] Update the com.liferay.gradle.plugins dependency to version
3.13.5.

## 1.10.8 - 2018-10-19

### Commits
- [LPS-86528] As used (89449135e6)
- [LPS-86528] refactor version handling to new util and add tests (a36607297f)
- [LPS-71117] Test plugins with Gradle up to 3.5.1 (c3e12d1cf3)
- [LPS-80660] Fix gradleTest (f804f81770)
- [LPS-84119] Remove unused methods (74dba76ca9)

### Dependencies
- [LPS-85556] Update the com.liferay.gradle.plugins dependency to version
3.13.4.
- [LPS-86493] Update the com.liferay.gradle.plugins dependency to version
3.13.3.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.13.2.
- [LPS-86413] Update the com.liferay.gradle.plugins dependency to version
3.13.1.
- [LPS-86018] Update the com.liferay.gradle.plugins dependency to version
3.13.0.
- [LPS-86447] Update the com.liferay.gradle.plugins dependency to version
3.12.169.
- [LPS-85678] Update the com.liferay.gradle.plugins dependency to version
3.12.168.
- [LPS-85678] Update the com.liferay.gradle.plugins dependency to version
3.12.167.
- [LPS-85556] Update the com.liferay.gradle.plugins dependency to version
3.12.166.
- [LPS-85849] Update the com.liferay.gradle.plugins dependency to version
3.12.165.
- [LPS-86324] Update the com.liferay.gradle.plugins dependency to version
3.12.164.
- [LPS-86362] Update the com.liferay.gradle.plugins dependency to version
3.12.163.
- [LPS-85954] Update the com.liferay.gradle.plugins dependency to version
3.12.162.
- [LPS-86371] Update the com.liferay.gradle.plugins dependency to version
3.12.161.
- [LPS-86308] Update the com.liferay.gradle.plugins dependency to version
3.12.160.
- [LPS-85959] Update the com.liferay.gradle.plugins dependency to version
3.12.159.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.158.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.157.
- [LPS-85946] Update the com.liferay.gradle.plugins.target.platform dependency
to version 1.1.3.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.156.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.155.
- [LPS-80388] Update the com.liferay.gradle.plugins dependency to version
3.12.154.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.153.
- [LPS-85987] Update the com.liferay.gradle.plugins dependency to version
3.12.152.
- [LPS-85959] Update the com.liferay.gradle.plugins dependency to version
3.12.151.
- [LPS-84138] Update the com.liferay.gradle.plugins dependency to version
3.12.150.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.149.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.148.
- [LPS-85556] Update the com.liferay.gradle.plugins dependency to version
3.12.147.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.146.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.145.
- [LPS-85678] Update the com.liferay.gradle.plugins dependency to version
3.12.144.
- [LPS-85609] Update the com.liferay.gradle.plugins dependency to version
3.12.143.
- [LPS-71117] Update the com.liferay.gradle.plugins dependency to version
3.12.142.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.141.
- [LPS-85296] Update the com.liferay.gradle.plugins dependency to version
3.12.140.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.139.
- [LPS-85035] Update the com.liferay.gradle.plugins dependency to version
3.12.138.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.137.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.136.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.135.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.134.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.133.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.132.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.131.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.130.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.129.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.128.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.127.
- [LPS-85092] Update the com.liferay.gradle.plugins dependency to version
3.12.126.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.125.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.124.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.123.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.122.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.121.
- [] Update the com.liferay.gradle.plugins dependency to version 3.12.120.
- [LPS-84621] Update the com.liferay.gradle.plugins dependency to version
3.12.119.
- [LPS-84887] Update the com.liferay.gradle.plugins dependency to version
3.12.118.
- [LPS-84094] Update the com.liferay.gradle.plugins dependency to version
3.12.117.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.116.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.115.
- [LPS-83067] Update the com.liferay.gradle.plugins dependency to version
3.12.114.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.113.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.112.
- [LPS-84624] Update the com.liferay.gradle.plugins dependency to version
3.12.111.
- [LPS-83790] Update the com.liferay.gradle.plugins dependency to version
3.12.110.
- [LPS-84473] Update the com.liferay.gradle.plugins dependency to version
3.12.109.
- [LPS-84039] Update the com.liferay.gradle.plugins dependency to version
3.12.108.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.107.
- [LPS-84039] Update the com.liferay.gradle.plugins dependency to version
3.12.106.
- [LPS-84307] Update the com.liferay.gradle.plugins dependency to version
3.12.105.

## 1.10.7 - 2018-08-08

### Commits
- [LPS-83922] Simplify (410f1cbc22)
- [LPS-83922] Initialize variables (6ff0bb39c7)
- [LPS-83922] Remove unneeded final keyword (2f66bc9cfb)
- [LPS-83922] consistency (181597a334)
- [LPS-83922] remove unneeded test (f49c4361cd)
- [LPS-83922] add compile.only bom as well (5699edb259)
- [LPS-83922] fix test (2611837d50)
- [LPS-83922] remove version fix since ids have changed (7a65ea86a2)
- [LPS-83922] add tests (1fcd0af2f0)
- [LPS-83922] add support for new BOM ids including DXP ids (bf7b639d35)

### Dependencies
- [LPS-83922] Update the com.liferay.gradle.plugins.target.platform dependency
to version 1.1.2.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.104.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.103.

### Description
- [LPS-83922] Adjust the target platform configuration to use new BOM IDs.

## 1.10.6 - 2018-08-07

### Commits
- [BLADE-255] Sort ignores (64e43cce91)
- [BLADE-255] normalize (8f80049156)
- [BLADE-255] fix test (54a5c179da)
- [BLADE-255] use plugins-sdk with dependencies (ff9eeddaa7)
- [BLADE-255] unneeded (dab0907408)
- [BLADE-255] fix test (7eaa0b3af9)
- [BLADE-255] rename (bdad4314ac)
- [BLADE-255] put themes in osgi/war and test (343b4ee4ab)
- [LPS-82828] Revert "LPS-82828 Deprecated as of 7.1.0" (470150b661)

### Dependencies
- [LPS-84055] Update the com.liferay.gradle.plugins dependency to version
3.12.102.
- [LPS-84213] Update the com.liferay.gradle.plugins dependency to version
3.12.101.
- [LPS-84119] Update the com.liferay.gradle.plugins dependency to version
3.12.100.
- [LPS-78033] Update the com.liferay.gradle.plugins dependency to version
3.12.99.
- [LPS-83705] Update the com.liferay.gradle.plugins dependency to version
3.12.98.
- [LPS-84055] Update the com.liferay.gradle.plugins dependency to version
3.12.97.
- [LPS-83755] Update the com.liferay.gradle.plugins.theme.builder dependency to
version 2.0.4.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.96.
- [LPS-83168] Update the com.liferay.gradle.plugins dependency to version
3.12.95.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.94.
- [LPS-84039] Update the com.liferay.gradle.plugins dependency to version
3.12.93.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.92.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.91.
- [LPS-78938] Update the com.liferay.gradle.plugins dependency to version
3.12.90.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.89.
- [LPS-83705] Update the com.liferay.gradle.plugins dependency to version
3.12.88.
- [LPS-83761] Update the com.liferay.gradle.plugins dependency to version
3.12.87.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.86.
- [LPS-82960] Update the com.liferay.gradle.plugins dependency to version
3.12.85.

### Description
- [BLADE-255] Copy themes into `osgi/war` instead of `osgi/modules`.

## 1.10.5 - 2018-07-23

### Dependencies
- [LPS-82491] Update the com.liferay.gradle.plugins.target.platform dependency
to version 1.1.1.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.84.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.83.
- [LPS-83483] Update the com.liferay.gradle.plugins dependency to version
3.12.82.
- [LPS-83576] Update the com.liferay.gradle.plugins dependency to version
3.12.81.
- [LPS-83520] Update the com.liferay.gradle.plugins dependency to version
3.12.80.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.79.

## 1.10.4 - 2018-07-17

### Commits
- [LPS-80660] update to 7.1 ga1 (1c8bb1ba17)
- [LPS-80660] update bundle url to latest (5a2337f291)

### Dependencies
- [LPS-80660] Update the com.liferay.gradle.plugins.target.platform dependency
to version 1.1.0.
- [LPS-80660] Update the com.liferay.portal.tools.bundle.support dependency to
version 3.2.6.
- [LPS-77699] Update the com.liferay.gradle.plugins dependency to version
3.12.78.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.77.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.76.
- [LPS-77699] Update the com.liferay.gradle.plugins dependency to version
3.12.75.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.74.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.73.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.72.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.71.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.70.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.69.
- [LPS-82960] Update the com.liferay.gradle.plugins dependency to version
3.12.68.
- [LPS-83220] Update the com.liferay.gradle.plugins dependency to version
3.12.67.
- [LPS-82828] Update the com.liferay.gradle.plugins dependency to version
3.12.66.
- [LPS-77359] Update the com.liferay.gradle.plugins.poshi.runner dependency to
version 2.2.5.
- [LPS-82828] Update the com.liferay.gradle.plugins dependency to version
3.12.65.
- [LPS-83067] Update the com.liferay.gradle.plugins dependency to version
3.12.64.
- [LPS-82976] Update the com.liferay.gradle.plugins dependency to version
3.12.63.
- [LPS-83104] Update the com.liferay.gradle.plugins dependency to version
3.12.62.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.61.
- [LPS-82343] Update the com.liferay.gradle.plugins dependency to version
3.12.60.

## 1.10.3 - 2018-06-28

### Commits
- [LPS-80660] set once (bd110d5489)
- [LPS-80660] add a test for 7.0-GA6 version (03f42de6ce)
- [LPS-80660] fixup a bad 7.0.6 version and throw exceptions for unrecognized
versions (c9d36536d8)
- [LPS-80660] more sf (5a18498031)
- [LPS-80660] add support for human understandable versions (f7ae33b377)
- [LPS-80660] update tests to use published BOMs (80c006831c)
- [LPS-82828] Deprecated as of 7.1.0 (69573bff7e)

### Dependencies
- [LPS-79679] Update the com.liferay.gradle.plugins dependency to version
3.12.59.
- [LPS-82828] Update the com.liferay.gradle.plugins dependency to version
3.12.58.
- [LPS-74608] Update the com.liferay.gradle.plugins dependency to version
3.12.57.
- [LPS-82857] Update the com.liferay.gradle.plugins dependency to version
3.12.56.
- [LPS-82828] Update the com.liferay.gradle.plugins dependency to version
3.12.55.
- [LPS-82568] Update the com.liferay.gradle.plugins dependency to version
3.12.54.
- [LPS-79679] Update the com.liferay.gradle.plugins dependency to version
3.12.53.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.52.
- [LPS-82433] Update the com.liferay.gradle.plugins dependency to version
3.12.51.
- [LPS-79679] Update the com.liferay.gradle.plugins dependency to version
3.12.50.
- [LPS-82420] Update the com.liferay.gradle.plugins dependency to version
3.12.49.

### Description
- [LPS-80660] Support commonly used Liferay Portal versions in the target
platform version.

## 1.10.2 - 2018-06-18

### Dependencies
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.48.
- [LPS-76226] Update the com.liferay.gradle.plugins dependency to version
3.12.47.

## 1.10.1 - 2018-06-15

### Commits
- [IDE-4081] update (801894ea46)

### Dependencies
- [LPS-82534] Update the com.liferay.gradle.plugins dependency to version
3.12.46.
- [LPS-79679] Update the com.liferay.gradle.plugins dependency to version
3.12.45.
- [LPS-77425] Update the com.liferay.gradle.plugins dependency to version
3.12.44.
- [LPS-77875] Update the com.liferay.gradle.plugins dependency to version
3.12.43.
- [LPS-77875] Update the com.liferay.portal.tools.bundle.support dependency to
version 3.2.5.
- [LPS-77875] Update the com.liferay.gradle.plugins dependency to version
3.12.42.
- [LPS-77875] Update the com.liferay.portal.tools.bundle.support dependency to
version 3.2.4.
- [LPS-77425] Update the com.liferay.gradle.plugins dependency to version
3.12.41.
- [LPS-82343] Update the com.liferay.gradle.plugins dependency to version
3.12.40.
- [LPS-82261] Update the com.liferay.gradle.plugins dependency to version
3.12.39.
- [LPS-82261] Update the com.liferay.gradle.plugins dependency to version
3.12.38.
- [LPS-77875] Update the com.liferay.gradle.plugins dependency to version
3.12.37.
- [LPS-77875] Update the com.liferay.portal.tools.bundle.support dependency to
version 3.2.3.
- [LPS-82121] Update the com.liferay.gradle.plugins dependency to version
3.12.36.
- [LPS-81638] Update the com.liferay.gradle.plugins dependency to version
3.12.35.
- [LPS-80927] Update the com.liferay.gradle.plugins dependency to version
3.12.34.
- [LPS-82178] Update the com.liferay.gradle.plugins dependency to version
3.12.33.
- [LPS-78940] Update the com.liferay.gradle.plugins dependency to version
3.12.32.
- [LPS-81944] Update the com.liferay.gradle.plugins dependency to version
3.12.31.
- [LPS-82001] Update the com.liferay.gradle.plugins dependency to version
3.12.30.
- [LPS-81336] Update the com.liferay.gradle.plugins dependency to version
3.12.29.
- [LPS-79919] Update the com.liferay.gradle.plugins dependency to version
3.12.28.
- [LPS-81895] Update the com.liferay.gradle.plugins dependency to version
3.12.27.
- [LPS-79679] Update the com.liferay.gradle.plugins dependency to version
3.12.26.
- [LPS-81795] Update the com.liferay.gradle.plugins dependency to version
3.12.25.
- [LPS-79679] Update the com.liferay.gradle.plugins dependency to version
3.12.24.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.12.23.
- [LPS-79679] Update the com.liferay.gradle.plugins dependency to version
3.12.22.
- [LPS-81635] Update the com.liferay.gradle.plugins dependency to version
3.12.21.
- [LPS-81555] Update the com.liferay.gradle.plugins dependency to version
3.12.20.

### Description
- [LPS-77875] Update the private constant `_DEFAULT_REPOSITORY_URL` to
`https://repository-cdn.liferay.com/nexus/content/groups/public`.

## 1.10.0 - 2018-05-26

### Commits
- [LPS-79653] Portlet 3.0: Upgrade to the Portlet 3.0.0 API (upgrade templates
and unit tests) (045479bf7c)
- [LPS-79653] Portlet 3.0: Upgrade to the Portlet 3.0.0 API (upgrade modules)
(65a8772c08)

### Dependencies
- [LPS-80517] Update the com.liferay.gradle.plugins dependency to version
3.12.19.
- [LPS-81404] Update the com.liferay.gradle.plugins dependency to version
3.12.18.
- [LPS-79709] Update the com.liferay.gradle.plugins dependency to version
3.12.17.
- [LPS-80723] Update the com.liferay.gradle.plugins dependency to version
3.12.16.
- [LPS-79679] Update the com.liferay.gradle.plugins dependency to version
3.12.15.
- [LPS-80777] Update the com.liferay.gradle.plugins dependency to version
3.12.14.
- [LPS-79963] Update the com.liferay.gradle.plugins dependency to version
3.12.13.
- [LPS-80920] Update the com.liferay.gradle.plugins dependency to version
3.12.12.
- [LPS-80517] Update the com.liferay.gradle.plugins dependency to version
3.12.11.
- [LPS-81106] Update the com.liferay.gradle.plugins dependency to version
3.12.10.
- [LPS-79240] Update the com.liferay.gradle.plugins dependency to version
3.12.9.
- [LPS-79679] Update the com.liferay.gradle.plugins dependency to version
3.12.8.
- [LPS-80950] Update the com.liferay.gradle.plugins.poshi.runner dependency to
version 2.2.4.
- [LPS-79679] Update the com.liferay.gradle.plugins dependency to version
3.12.7.
- [LPS-79262] Update the com.liferay.gradle.plugins dependency to version
3.12.6.
- [LPS-80944] Update the com.liferay.gradle.plugins dependency to version
3.12.5.
- [LPS-78940] Update the com.liferay.gradle.plugins dependency to version
3.12.4.
- [LPS-79679] Update the com.liferay.gradle.plugins dependency to version
3.12.3.
- [LPS-79799] Update the com.liferay.gradle.plugins dependency to version
3.12.2.
- [LPS-80840] Update the com.liferay.gradle.plugins dependency to version
3.12.1.

### Description
- [LPS-79453] Add support for Ext OSGi modules.
- [LPS-79453] Add support for Ext plugins.

## 1.9.3 - 2018-05-10

### Commits
- [LPS-79453] Let's call them "Ext plugin" and "Ext OSGi module" (22965a6320)
- [LPS-79453] Simplify (68dbf7ae0e)
- [LPS-79453] Make it "final" only when needed (b4215b6e60)
- [LPS-79453] add ExtProjectConfigurator (ext folder) to workspace (f66d06b86e)

### Dependencies
- [LPS-79453] Update the com.liferay.gradle.plugins dependency to version
3.12.0.
- [LPS-80332] Update the com.liferay.gradle.plugins dependency to version
3.11.39.
- [LPS-80544] Update the com.liferay.gradle.plugins dependency to version
3.11.38.
- [LPS-80513] Update the com.liferay.gradle.plugins dependency to version
3.11.37.
- [LPS-75530] Update the com.liferay.gradle.plugins dependency to version
3.11.36.
- [LPS-80222] Update the com.liferay.gradle.plugins.target.platform dependency
to version 1.0.1.
- [LPS-79679] Update the com.liferay.gradle.plugins dependency to version
3.11.35.
- [LPS-80517] Update the com.liferay.gradle.plugins dependency to version
3.11.34.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.11.33.
- [LPS-79679] Update the com.liferay.gradle.plugins dependency to version
3.11.32.
- [LPS-80466] Update the com.liferay.gradle.plugins dependency to version
3.11.31.
- [LPS-80386] Update the com.liferay.gradle.plugins dependency to version
3.11.30.

## 1.9.2 - 2018-05-02

### Commits
- [LPS-79239] Update Liferay Workspace to latest GA6 (18c42f9730)

### Dependencies
- [LPS-80394] Update the com.liferay.gradle.plugins dependency to version
3.11.29.
- [LPS-80394] Update the com.liferay.gradle.plugins.poshi.runner dependency to
version 2.2.3.
- [] Update the com.liferay.gradle.plugins.poshi.runner dependency to version
2.2.2.
- [LPS-80332] Update the com.liferay.gradle.plugins dependency to version
3.11.28.
- [LPS-79679] Update the com.liferay.gradle.plugins dependency to version
3.11.27.
- [LPS-79679] Update the com.liferay.gradle.plugins dependency to version
3.11.26.
- [LPS-80122] Update the com.liferay.gradle.plugins dependency to version
3.11.25.
- [LPS-79679] Update the com.liferay.gradle.plugins dependency to version
3.11.24.
- [LPS-79755] Update the com.liferay.gradle.plugins dependency to version
3.11.23.
- [LPS-80123] Update the com.liferay.gradle.plugins dependency to version
3.11.22.
- [LPS-80125] Update the com.liferay.gradle.plugins dependency to version
3.11.21.
- [LPS-80184] Update the com.liferay.gradle.plugins dependency to version
3.11.20.
- [LPS-79388] Update the com.liferay.gradle.plugins dependency to version
3.11.19.
- [LPS-79963] Update the com.liferay.gradle.plugins dependency to version
3.11.18.
- [LPS-80064] Update the com.liferay.gradle.plugins dependency to version
3.11.17.
- [LPS-80054] Update the com.liferay.gradle.plugins dependency to version
3.11.16.
- [LPS-80054] Update the com.liferay.gradle.plugins dependency to version
3.11.15.
- [LPS-79799] Update the com.liferay.gradle.plugins dependency to version
3.11.14.
- [LPS-75049] Update the com.liferay.gradle.plugins dependency to version
3.11.13.
- [LPS-72705] Update the com.liferay.gradle.plugins dependency to version
3.11.12.
- [LPS-79919] Update the com.liferay.gradle.plugins dependency to version
3.11.11.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.11.10.
- [LPS-79386] Update the com.liferay.gradle.plugins dependency to version
3.11.9.
- [LPS-77645] Update the com.liferay.gradle.plugins dependency to version
3.11.8.
- [LPS-79755] Update the com.liferay.gradle.plugins dependency to version
3.11.7.
- [LPS-79679] Update the com.liferay.gradle.plugins dependency to version
3.11.6.
- [LPS-79576] Update the com.liferay.gradle.plugins dependency to version
3.11.5.
- [LPS-79576] Update the com.liferay.gradle.plugins dependency to version
3.11.4.
- [LPS-79623] Update the com.liferay.gradle.plugins dependency to version
3.11.3.
- [LPS-77639] Update the com.liferay.gradle.plugins dependency to version
3.11.2.
- [LPS-79576] Update the com.liferay.gradle.plugins dependency to version
3.11.1.
- [LPS-75530] Update the com.liferay.gradle.plugins dependency to version
3.11.0.
- [LPS-79576] Update the com.liferay.gradle.plugins dependency to version
3.10.18.
- [LPS-78459] Update the com.liferay.gradle.plugins dependency to version
3.10.17.
- [LPS-75010] Update the com.liferay.gradle.plugins dependency to version
3.10.16.
- [LPS-78911] Update the com.liferay.gradle.plugins dependency to version
3.10.15.
- [LPS-78308] Update the com.liferay.gradle.plugins dependency to version
3.10.14.
- [LPS-74171] Update the com.liferay.gradle.plugins dependency to version
3.10.13.
- [LPS-79450] Update the com.liferay.gradle.plugins dependency to version
3.10.12.
- [LPS-78971] Update the com.liferay.gradle.plugins dependency to version
3.10.11.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.10.10.
- [LPS-75049] Update the com.liferay.gradle.plugins dependency to version
3.10.9.
- [LPS-79365] Update the com.liferay.gradle.plugins dependency to version
3.10.8.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.10.7.
- [LPS-79360] Update the com.liferay.gradle.plugins dependency to version
3.10.6.
- [LPS-74110] Update the com.liferay.gradle.plugins dependency to version
3.10.5.
- [LPS-75010] Update the com.liferay.gradle.plugins dependency to version
3.10.4.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.10.3.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.10.2.
- [LPS-79248] Update the com.liferay.gradle.plugins dependency to version
3.10.1.
- [LPS-78741] Update the com.liferay.gradle.plugins dependency to version
3.10.0.
- [LPS-79239] Update the com.liferay.portal.tools.bundle.support dependency to
version 3.2.2.

## 1.9.1 - 2018-03-30

### Dependencies
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.9.22.
- [LPS-79286] Update the com.liferay.gradle.plugins dependency to version
3.9.21.
- [LPS-78901] Update the com.liferay.gradle.plugins dependency to version
3.9.20.
- [LPS-79226] Update the com.liferay.gradle.plugins dependency to version
3.9.19.
- [LPS-79131] Update the com.liferay.gradle.plugins dependency to version
3.9.18.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.9.17.
- [LPS-77577] Update the com.liferay.gradle.plugins dependency to version
3.9.16.
- [LPS-78477] Update the com.liferay.gradle.plugins dependency to version
3.9.15.
- [LPS-79192] Update the com.liferay.gradle.plugins dependency to version
3.9.14.
- [LPS-79191] Update the com.liferay.gradle.plugins dependency to version
3.9.13.

## 1.9.0 - 2018-03-26

### Commits
- [LPS-77425] Set portal version based on bundle URL (75ca3288c2)
- [LPS-77425] Partial revert of d25f48516a9ad080bcbd50e228979853d3f2dda5
(60d3a950d6)
- [LPS-77425] Partial revert of b739c8fcdc5d1546bd642ca931476c71bbaef1fb
(02ca75b1da)
- [LPS-77425] Increment all major versions (d25f48516a)
- [LPS-78149] Simplify (f000483590)
- [LPS-78149] Add packageinfo (418d642432)
- [LPS-78149] pmd check (915a92ccb7)

### Dependencies
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.9.12.
- [LPS-78911] Update the com.liferay.gradle.plugins dependency to version
3.9.11.
- [LPS-78741] Update the com.liferay.gradle.plugins dependency to version
3.9.10.
- [LPS-78772] Update the com.liferay.gradle.plugins dependency to version 3.9.9.
- [LPS-78750] Update the com.liferay.gradle.plugins dependency to version 3.9.8.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version 3.9.7.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version 3.9.6.
- [LPS-78772] Update the com.liferay.gradle.plugins dependency to version 3.9.5.
- [LPS-78772] Update the com.liferay.gradle.plugins dependency to version 3.9.4.
- [LPS-78772] Update the com.liferay.gradle.plugins dependency to version 3.9.3.
- [LPS-78911] Update the com.liferay.gradle.plugins dependency to version 3.9.2.
- [LPS-78772] Update the com.liferay.gradle.plugins dependency to version 3.9.1.
- [LPS-77425] Update the com.liferay.gradle.plugins dependency to version 3.9.0.
- [LPS-78845] Update the com.liferay.gradle.plugins dependency to version
3.8.21.
- [LPS-78772] Update the com.liferay.gradle.plugins dependency to version
3.8.20.
- [LPS-78741] Update the com.liferay.gradle.plugins dependency to version
3.8.19.
- [LPS-77425] Update the com.liferay.gradle.plugins dependency to version
3.8.18.
- [LPS-77425] Update the com.liferay.gradle.plugins.poshi.runner dependency to
version 2.2.1.
- [LPS-77425] Update the com.liferay.gradle.plugins.target.platform dependency
to version 1.0.0.
- [LPS-77425] Update the com.liferay.gradle.plugins.theme.builder dependency to
version 2.0.3.
- [LPS-77425] Update the com.liferay.portal.tools.bundle.support dependency to
version 3.2.1.
- [LPS-77425] Update the gradle-download-task dependency to version 3.2.0.
- [LPS-77425] Update the gradle-download-task dependency to version 3.2.0.
- [LPS-78767] Update the com.liferay.gradle.plugins dependency to version
3.8.18.
- [LPS-78457] Update the com.liferay.gradle.plugins dependency to version
3.8.17.
- [LPS-78269] Update the com.liferay.gradle.plugins dependency to version
3.8.16.
- [LPS-78308] Update the com.liferay.gradle.plugins dependency to version
3.8.15.
- [LPS-78288] Update the com.liferay.gradle.plugins dependency to version
3.8.14.
- [LPS-78571] Update the com.liferay.gradle.plugins dependency to version
3.8.13.
- [LPS-78493] Update the com.liferay.gradle.plugins dependency to version
3.8.12.
- [LPS-78459] Update the com.liferay.gradle.plugins dependency to version
3.8.11.
- [LPS-78571] Update the com.liferay.gradle.plugins dependency to version
3.8.10.
- [LPS-77425] Update the com.liferay.gradle.plugins dependency to version 3.8.9.
- [LPS-78537] Update the com.liferay.gradle.plugins dependency to version 3.8.8.
- [LPS-78537] Update the com.liferay.gradle.plugins.poshi.runner dependency to
version 2.2.1.
- [LPS-78312] Update the com.liferay.gradle.plugins dependency to version 3.8.7.

### Description
- [LPS-77425] Automatically set the `portal.version` property for all projects
based on the value of the `liferay.workspace.bundle.url` property.
- [LPS-77586] Add support for relative file URLs in the
`liferay.workspace.bundle.url` project property.
- [LPS-78149] Add the ability to deploy additional 3rd-party OSGi modules via
the `providedModules` configuration of the root project.
- [LPS-78149] Apply the
[`com.liferay.target.platform.ide`](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/gradle-plugins-target-platform)
Gradle plugin to the root project if the `target.platform.version` property in
`gradle.properties` is set.

## 1.8.1 - 2018-03-05

### Commits
- [LPS-78149] baseline (986a376e1e)
- [LPS-78149] add new workspace tests for TP features (cd1459bc8d)
- [LPS-78149] use normal dependencies for boms configuration (af2a787611)
- [LPS-78149] Consistency (adc706b1ad)
- [LPS-78149] Wordsmith (0c5ab4ddf2)
- [LPS-78149] Isolate and use default dependencies (308dc2dc9b)
- [LPS-78149] Rename (53bd63153b)
- [LPS-78149] apply and configure target platform ide plugin based on sensible
defaults (aa2dcbd314)
- [LPS-78149] add new providedModules configurations that is added to
'osgi/modules' (1aaf7a431c)
- [LPS-77352] Move to gradle-plugins (e5dba5c05e)
- [LPS-77532] ExtPlugin for Liferay Workspace (gradle) (a48623efd4)
- [LPS-77586] Avoid chaining (70fbacf8df)
- [LPS-77586] support relative file urls (b9200d2c02)
- [LPS-76221] republish (c7b9a54d12)
- [LPS-76221] release new jars as part of revert (73dc6a86e0)
- [LPS-74544] use (cbcf29f2c3)

### Dependencies
- [LPS-78149] Update the com.liferay.gradle.plugins.target.platform dependency
to version 1.0.0.
- [LPS-76997] Update the com.liferay.gradle.plugins dependency to version 3.8.6.
- [LPS-78312] Update the com.liferay.gradle.plugins dependency to version 3.8.5.
- [LPS-76926] Update the com.liferay.gradle.plugins dependency to version 3.8.4.
- [LPS-78266] Update the com.liferay.gradle.plugins dependency to version 3.8.3.
- [LPS-78266] Update the com.liferay.gradle.plugins.poshi.runner dependency to
version 2.2.0.
- [LPS-75323] Update the com.liferay.gradle.plugins dependency to version 3.8.2.
- [LPS-76926] Update the com.liferay.gradle.plugins dependency to version 3.8.1.
- [LPS-77532] Update the com.liferay.gradle.plugins dependency to version 3.8.0.
- [LPS-78150] Update the com.liferay.gradle.plugins dependency to version 3.7.9.
- [LPS-78033] Update the com.liferay.gradle.plugins dependency to version 3.7.8.
- [LPS-78071] Update the com.liferay.gradle.plugins dependency to version 3.7.7.
- [LPS-78096] Update the com.liferay.gradle.plugins dependency to version 3.7.6.
- [LPS-78096] Update the com.liferay.gradle.plugins.poshi.runner dependency to
version 2.1.0.
- [LPS-78038] Update the com.liferay.gradle.plugins dependency to version 3.7.5.
- [LPS-78033] Update the com.liferay.gradle.plugins dependency to version 3.7.4.
- [LPS-77996] Update the com.liferay.gradle.plugins dependency to version 3.7.3.
- [LPS-68297] Update the com.liferay.gradle.plugins dependency to version 3.7.2.
- [LPS-77916] Update the com.liferay.gradle.plugins dependency to version 3.7.1.
- [LPS-77840] Update the com.liferay.gradle.plugins dependency to version 3.7.0.
- [LPS-69802] Update the com.liferay.gradle.plugins dependency to version 3.6.4.
- [LPS-77886] Update the com.liferay.gradle.plugins dependency to version 3.6.3.
- [LPS-77836] Update the com.liferay.gradle.plugins.poshi.runner dependency to
version 2.0.0.
- [LPS-77836] Update the com.liferay.gradle.plugins dependency to version 3.6.2.
- [LPS-77795] Update the com.liferay.gradle.plugins dependency to version 3.6.1.
- [LPS-77350] Update the com.liferay.gradle.plugins dependency to version 3.6.0.
- [LPS-77459] Update the com.liferay.gradle.plugins dependency to version
3.5.101.
- [LPS-77630] Update the com.liferay.gradle.plugins dependency to version
3.5.100.
- [LPS-77441] Update the com.liferay.gradle.plugins dependency to version
3.5.99.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.5.98.
- [LPS-77423] Update the com.liferay.gradle.plugins dependency to version
3.5.97.
- [LPS-77143] Update the com.liferay.gradle.plugins dependency to version
3.5.96.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.5.95.
- [LPS-77400] Update the com.liferay.gradle.plugins dependency to version
3.5.94.
- [LPS-77402] Update the com.liferay.gradle.plugins dependency to version
3.5.93.
- [LPS-77286] Update the com.liferay.gradle.plugins dependency to version
3.5.92.
- [LPS-76644] Update the com.liferay.gradle.plugins dependency to version
3.5.91.
- [LPS-76626] Update the com.liferay.gradle.plugins dependency to version
3.5.90.
- [LPS-77110] Update the com.liferay.gradle.plugins dependency to version
3.5.89.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.5.88.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.5.87.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.5.86.
- [LPS-76226] Update the com.liferay.gradle.plugins dependency to version
3.5.85.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.5.84.
- [LPS-76626] Update the com.liferay.gradle.plugins dependency to version
3.5.83.
- [LPS-76840] Update the com.liferay.gradle.plugins dependency to version
3.5.82.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.5.81.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.5.80.
- [LPS-74904] Update the com.liferay.gradle.plugins dependency to version
3.5.79.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.5.78.
- [LRDOCS-4111] Update the com.liferay.gradle.plugins dependency to version
3.5.77.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.5.76.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version
3.5.75.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.5.74.
- [LPS-76626] Update the com.liferay.gradle.plugins dependency to version
3.5.73.
- [LPS-76221] Update the com.liferay.gradle.plugins dependency to version
3.5.72.
- [LPS-76221] Update the com.liferay.gradle.plugins dependency to version
3.5.71.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.5.70.
- [LPS-76475] Update the com.liferay.gradle.plugins dependency to version
3.5.69.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.5.68.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.5.67.
- [LPS-76018] Update the com.liferay.gradle.plugins dependency to version
3.5.66.
- [LPS-76018] Update the com.liferay.gradle.plugins dependency to version
3.5.65.
- [LPS-76018] Update the com.liferay.gradle.plugins dependency to version
3.5.64.
- [LPS-72912] Update the com.liferay.gradle.plugins dependency to version
3.5.63.
- [LPS-76326] Update the com.liferay.gradle.plugins dependency to version
3.5.62.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.5.61.
- [LPS-76226] Update the com.liferay.gradle.plugins dependency to version
3.5.60.
- [LPS-76256] Update the com.liferay.gradle.plugins dependency to version
3.5.59.

## 1.8.0 - 2017-12-05

### Commits
- [LPS-76271] Add Gradle test (b30bc8149c)

### Dependencies
- [LPS-76221] Update the com.liferay.gradle.plugins dependency to version
3.5.58.

### Description
- [LPS-76271] Add the ability to build theme projects with the
[Liferay Portal Tools Theme Builder]. To enable this, set the
`liferay.workspace.themes.java.build` property to `true` in `gradle.properties`.

## 1.7.2 - 2017-12-05

### Commits
- [LPS-76271] Expose all other configurator names for consistency (215297d21e)
- [LPS-76271] Reuse WAR configurator (6c30c46393)
- [LPS-76271] Typo (8292c188b7)
- [LPS-76271] Rename property to "java.build" and move to theme configurator
(cfbb7a60dd)
- [LPS-76271] add option to build node themes via theme builder (e057dc9901)
- [LPS-73725] for LangBuilder to use new constants in
https://github.com/liferay/liferay-portal/commit/4bf57ddfe3f6 (6d48debbe9)

### Dependencies
- [LPS-76271] Update the com.liferay.gradle.plugins.theme.builder dependency to
version 2.0.3.
- [LPS-76221] Update the com.liferay.gradle.plugins dependency to version
3.5.57.
- [LPS-76224] Update the com.liferay.gradle.plugins dependency to version
3.5.56.
- [LPS-76202] Update the com.liferay.gradle.plugins dependency to version
3.5.55.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.5.54.
- [LPS-75859] Update the com.liferay.gradle.plugins dependency to version
3.5.53.
- [LPS-75901] Update the com.liferay.gradle.plugins dependency to version
3.5.52.
- [LPS-75859] Update the com.liferay.gradle.plugins dependency to version
3.5.51.
- [LPS-72912] Update the com.liferay.gradle.plugins dependency to version
3.5.50.
- [LPS-76145] Update the com.liferay.gradle.plugins dependency to version
3.5.49.
- [LPS-76110] Update the com.liferay.gradle.plugins dependency to version
3.5.48.
- [LPS-74457] Update the com.liferay.gradle.plugins dependency to version
3.5.47.
- [LPS-75965] Update the com.liferay.gradle.plugins dependency to version
3.5.46.
- [LPS-75798] Update the com.liferay.gradle.plugins dependency to version
3.5.45.
- [LPS-75798] Update the com.liferay.gradle.plugins dependency to version
3.5.44.
- [LPS-75798] Update the com.liferay.gradle.plugins dependency to version
3.5.43.
- [LPS-74526] Update the com.liferay.gradle.plugins dependency to version
3.5.42.
- [LPS-74526] Update the com.liferay.gradle.plugins dependency to version
3.5.41.
- [LPS-75798] Update the com.liferay.gradle.plugins dependency to version
3.5.40.
- [LPS-75010] Update the com.liferay.gradle.plugins dependency to version
3.5.39.
- [LPS-75610] Update the com.liferay.gradle.plugins dependency to version
3.5.38.
- [LPS-73725] Update the com.liferay.gradle.plugins dependency to version
3.5.37.
- [LPS-73725] Update the com.liferay.gradle.plugins dependency to version
3.5.36.
- [LPS-73725] Update the com.liferay.gradle.plugins dependency to version
3.5.35.
- [LPS-73725] Update the com.liferay.gradle.plugins dependency to version
3.5.34.
- [LPS-74457] Update the com.liferay.gradle.plugins dependency to version
3.5.33.
- [LPS-74457] Update the com.liferay.gradle.plugins dependency to version
3.5.32.
- [LPS-75633] Update the com.liferay.gradle.plugins dependency to version
3.5.31.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.5.30.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.5.29.
- [LPS-75705] Update the com.liferay.gradle.plugins dependency to version
3.5.28.
- [LPS-75399] Update the com.liferay.gradle.plugins dependency to version
3.5.27.
- [LPS-75589] Update the com.liferay.gradle.plugins dependency to version
3.5.26.
- [LPS-75613] Update the com.liferay.gradle.plugins dependency to version
3.5.25.
- [LPS-75488] Update the com.liferay.gradle.plugins dependency to version
3.5.24.

## 1.7.1 - 2017-10-29

### Commits
- [LPS-75479] Update everywhere else (73334fed0a)
- [LPS-69294] Sort tasks alphabetically (2fe67d5bda)
- [LPS-73481] Adding compatibility with 7.1.0 in liferay-look-and-feel.xml
(f2780b9d63)
- [LPS-73481] Adding compatibility with 7.1.0 in
liferay-plugin-package.properties (dc7de51e77)
- [LPS-74818] Fix Gradle test (67b685c3dd)
- [LPS-74818] Reuse new method (612f5370ae)
- [LPS-74818] Check directly from "downloadBundle" property values (617f71239f)
- [LPS-74818] Avoid NPE is bundle URL does not point to a local file
(4cbb9318bc)
- [LPS-74818] Configure both properties via Gradle for consistency (ad14ff3aa8)
- [LPS-74818] Make test more accurate (f8fab2ea9c)
- [LPS-74818] Add more assertions (2f6964c4de)
- [LPS-74818] try to call directly instead of dependsOn (a7f9ef0e40)
- [LPS-74818] rename (364c0719bf)
- [LPS-74818] Move out of configuration (0cd0d587fd)
- [LPS-74818] Add check for make sure destDir is not the same as srcDir
(78b4fda1e8)
- [LPS-74124] Not needed, cache dir is created automatically (7d5996215c)
- [LPS-74124] Simplify (3a3449022c)
- [LPS-74124] This is just a marker file to commit the empty directory
(e68c93b88d)
- [LPS-73056] Simplify and SF (c8ea22ce42)
- [LPS-74124] add customCacheDir test (2984834914)
- [LPS-73056] add customTokenFile test (8afbcb28e3)

### Dependencies
- [LPS-75479] Update the com.liferay.portal.tools.bundle.support dependency to
version 3.2.1.
- [LPS-75323] Update the com.liferay.gradle.plugins dependency to version
3.5.23.
- [LPS-74849] Update the com.liferay.gradle.plugins dependency to version
3.5.22.
- [LPS-74457] Update the com.liferay.gradle.plugins dependency to version
3.5.21.
- [LPS-75427] Update the com.liferay.gradle.plugins dependency to version
3.5.20.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.5.19.
- [LPS-74457] Update the com.liferay.gradle.plugins dependency to version
3.5.18.
- [LPS-75254] Update the com.liferay.gradle.plugins dependency to version
3.5.17.
- [LPS-74348] Update the com.liferay.gradle.plugins dependency to version
3.5.16.
- [LPS-74849] Update the com.liferay.gradle.plugins dependency to version
3.5.15.
- [LPS-74849] Update the com.liferay.gradle.plugins dependency to version
3.5.14.
- [LPS-75239] Update the com.liferay.gradle.plugins dependency to version
3.5.13.
- [LPS-75100] Update the com.liferay.gradle.plugins dependency to version
3.5.12.
- [LPS-74849] Update the com.liferay.gradle.plugins dependency to version
3.5.11.
- [LPS-75273] Update the com.liferay.gradle.plugins dependency to version
3.5.10.
- [LPS-75238] Update the com.liferay.gradle.plugins dependency to version 3.5.9.
- [LPS-74449] Update the com.liferay.gradle.plugins dependency to version 3.5.8.
- [LPS-75096] Update the com.liferay.gradle.plugins dependency to version 3.5.7.
- [LPS-75175] Update the com.liferay.gradle.plugins dependency to version 3.5.6.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version 3.5.5.
- [LPS-74143] Update the com.liferay.gradle.plugins dependency to version 3.5.4.
- [LPS-74426] Update the com.liferay.gradle.plugins dependency to version 3.5.3.
- [LPS-74143] Update the com.liferay.gradle.plugins dependency to version 3.5.2.
- [LPS-74872] Update the com.liferay.gradle.plugins dependency to version 3.5.1.
- [LPS-74314] Update the com.liferay.gradle.plugins dependency to version 3.5.0.
- [LPS-75039] Update the com.liferay.gradle.plugins dependency to version
3.4.74.
- [LPS-75039] Update the com.liferay.gradle.plugins dependency to version
3.4.75.
- [LPS-75039] Update the com.liferay.gradle.plugins.poshi.runner dependency to
version 1.0.12.
- [LPS-75009] Update the com.liferay.gradle.plugins dependency to version
3.4.74.
- [LPS-74933] Update the com.liferay.gradle.plugins dependency to version
3.4.73.
- [LPS-74867] Update the com.liferay.gradle.plugins dependency to version
3.4.72.
- [LPS-74749] Update the com.liferay.gradle.plugins dependency to version
3.4.71.
- [LPS-74884] Update the com.liferay.gradle.plugins dependency to version
3.4.70.
- [LPS-71117] Update the com.liferay.gradle.plugins dependency to version
3.4.69.
- [LPS-73070] Update the com.liferay.gradle.plugins dependency to version
3.4.68.
- [LPS-74657] Update the com.liferay.portal.tools.bundle.support dependency to
version 3.2.0.
- [LPS-74738] Update the com.liferay.gradle.plugins dependency to version
3.4.67.
- [LPS-74657] Update the com.liferay.gradle.plugins dependency to version
3.4.66.
- [LPS-74657] Update the com.liferay.gradle.plugins dependency to version
3.4.65.
- [LPS-74789] Update the com.liferay.gradle.plugins dependency to version
3.4.64.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.4.63.
- [LPS-74770] Update the com.liferay.gradle.plugins dependency to version
3.4.62.
- [LPS-74637] Update the com.liferay.gradle.plugins dependency to version
3.4.61.
- [LPS-74315] Update the com.liferay.gradle.plugins dependency to version
3.4.60.
- [LPS-74614] Update the com.liferay.gradle.plugins dependency to version
3.4.59.
- [LPS-74657] Update the com.liferay.gradle.plugins dependency to version
3.4.58.
- [LPS-74637] Update the com.liferay.gradle.plugins dependency to version
3.4.57.
- [LPS-74207] Update the com.liferay.gradle.plugins dependency to version
3.4.56.
- [LPS-74373] Update the com.liferay.gradle.plugins dependency to version
3.4.55.
- [LPS-74614] Update the com.liferay.gradle.plugins dependency to version
3.4.54.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.4.53.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.4.52.
- [LPS-74544] Update the com.liferay.gradle.plugins dependency to version
3.4.51.
- [LPS-74490] Update the com.liferay.gradle.plugins dependency to version
3.4.50.
- [LPS-74538] Update the com.liferay.gradle.plugins dependency to version
3.4.49.
- [LPS-74475] Update the com.liferay.gradle.plugins dependency to version
3.4.48.
- [LPS-72705] Update the com.liferay.gradle.plugins dependency to version
3.4.47.
- [LPS-74278] Update the com.liferay.gradle.plugins dependency to version
3.4.46.
- [LPS-73124] Update the com.liferay.gradle.plugins dependency to version
3.4.45.
- [LPS-73070] Update the com.liferay.gradle.plugins dependency to version
3.4.44.
- [LPS-72705] Update the com.liferay.gradle.plugins dependency to version
3.4.43.
- [LPS-74369] Update the com.liferay.gradle.plugins dependency to version
3.4.42.
- [LPS-74368] Update the com.liferay.gradle.plugins dependency to version
3.4.41.
- [LPS-72705] Update the com.liferay.gradle.plugins dependency to version
3.4.40.
- [LPS-74345] Update the com.liferay.gradle.plugins dependency to version
3.4.39.
- [LPS-74343] Update the com.liferay.gradle.plugins dependency to version
3.4.38.
- [LPS-74314] Update the com.liferay.gradle.plugins dependency to version
3.4.37.
- [LPS-74278] Update the com.liferay.gradle.plugins dependency to version
3.4.36.
- [LPS-74265] Update the com.liferay.gradle.plugins dependency to version
3.4.35.
- [LPS-74155] Update the com.liferay.gradle.plugins dependency to version
3.4.34.
- [LPS-74126] Update the com.liferay.gradle.plugins dependency to version
3.4.33.
- [LPS-74222] Update the com.liferay.gradle.plugins dependency to version
3.4.32.
- [LPS-74139] Update the com.liferay.gradle.plugins dependency to version
3.4.31.
- [LPS-74126] Update the com.liferay.gradle.plugins dependency to version
3.4.30.
- [LPS-74139] Update the com.liferay.gradle.plugins dependency to version
3.4.29.
- [LPS-74102] Update the com.liferay.gradle.plugins dependency to version
3.4.28.
- [LPS-74126] Update the com.liferay.gradle.plugins dependency to version
3.4.27.

### Description
- [LPS-74818] Fail the build if the source and destination of the
`downloadBundle` task are the same.
- [LPS-75479] Use Liferay 7.0.4 GA5 as the default bundle in a workspace.

## 1.7.0 - 2017-08-11

### Dependencies
- [LPS-73967] Update the com.liferay.gradle.plugins dependency to version
3.4.26.

### Description
- [LPS-74124] Add the ability to configure the cache directory for downloaded
Liferay bundles by setting the `liferay.workspace.bundle.cache.dir` property in
`gradle.properties`.

## 1.6.1 - 2017-08-10

### Commits
- [LPS-74124] Make download cache dir configurable (eb369429d2)

### Dependencies
- [LPS-74088] Update the com.liferay.gradle.plugins dependency to version
3.4.25.
- [LPS-73967] Update the com.liferay.gradle.plugins dependency to version
3.4.24.
- [LPS-74104] Update the com.liferay.gradle.plugins dependency to version
3.4.23.
- [LPS-74092] Update the com.liferay.gradle.plugins dependency to version
3.4.22.
- [LPS-74063] Update the com.liferay.gradle.plugins dependency to version
3.4.21.
- [LPS-74034] Update the com.liferay.gradle.plugins dependency to version
3.4.20.
- [LPS-73935] Update the com.liferay.gradle.plugins dependency to version
3.4.19.

## 1.6.0 - 2017-08-01

### Commits
- [LPS-73913] Read password file in Gradle (e75b391be2)
- [LPS-73913] Make Gradle check if the password file exists or not (1fa8a15149)
- [LPS-73913] Allow to have closures that return null as files (4c0c8dafe0)
- [LPS-73913] Allow to set the token password file via gradle.properties
(b94f908740)

### Dependencies
- [LPS-73855] Update the com.liferay.gradle.plugins dependency to version
3.4.18.
- [LPS-73913] Update the com.liferay.portal.tools.bundle.support dependency to
version 3.1.0.

### Description
- [LPS-73913] Add the ability to pass the Liferay bundle authentication token
password from a file by setting the
`liferay.workspace.bundle.token.password.file` property in `gradle.properties`.

## 1.5.2 - 2017-08-01

### Commits
- [LPS-73913] semver (9dfdc49652)
- [LPS-73913] use new api (90277f20c5)

### Dependencies
- [LPS-73913] Update the com.liferay.portal.tools.bundle.support dependency to
version 3.0.3.
- [LPS-73655] Update the com.liferay.gradle.plugins dependency to version
3.4.17.
- [LPS-72705] Update the com.liferay.gradle.plugins dependency to version
3.4.16.
- [LPS-73818] Update the com.liferay.gradle.plugins dependency to version
3.4.15.
- [LPS-72347] Update the com.liferay.gradle.plugins dependency to version
3.4.14.
- [LPS-73353] Update the com.liferay.gradle.plugins dependency to version
3.4.13.
- [LPS-72705] Update the com.liferay.gradle.plugins dependency to version
3.4.12.

## 1.5.1 - 2017-07-21

### Commits
- [LPS-73746] trim token content in case users add an extra newline into their
token files (5214260900)

### Dependencies
- [LPS-73746] Update the com.liferay.portal.tools.bundle.support dependency to
version 3.0.2.
- [LPS-72705] Update the com.liferay.gradle.plugins dependency to version
3.4.11.
- [LPS-73600] Update the com.liferay.gradle.plugins dependency to version
3.4.10.
- [LPS-72705] Update the com.liferay.gradle.plugins dependency to version 3.4.9.
- [LPS-72705] Update the com.liferay.gradle.plugins dependency to version 3.4.8.
- [LPS-72705] Update the com.liferay.gradle.plugins dependency to version 3.4.7.
- [LPS-73472] Update the com.liferay.gradle.plugins dependency to version 3.4.6.
- [LPS-73607] Update the com.liferay.gradle.plugins dependency to version 3.4.5.
- [LPS-73584] Update the com.liferay.gradle.plugins dependency to version 3.4.4.
- [LPS-73584] Update the com.liferay.gradle.plugins dependency to version 3.4.3.
- [LPS-73525] Update the com.liferay.gradle.plugins dependency to version 3.4.2.
- [LPS-72705] Update the com.liferay.gradle.plugins dependency to version 3.4.1.
- [LPS-73261] Update the com.liferay.gradle.plugins dependency to version 3.4.0.
- [LPS-72705] Update the com.liferay.gradle.plugins dependency to version
3.3.38.
- [LPS-73495] Update the com.liferay.gradle.plugins dependency to version
3.3.37.
- [LPS-72705] Update the com.liferay.gradle.plugins dependency to version
3.3.36.

### Description
- [LPS-73746] Trim authentication token in case users add extra lines into
their token file.

## 1.5.0 - 2017-07-05

### Commits
- [LPS-73506] Move properties to extension object (54503d24e3)
- [LPS-73506] Simplify (e2cf499437)
- [LPS-73506] Add "onlyIf" condition to all task instances (8ddabe909f)
- [LPS-73506] only create the token if there is no existing token file or
-Pforce=true has been set (c3c2bb250c)
- [LPS-73506] add -Pprop=value style arguments for email.address, force,
password (a891a7e63b)
- [LPS-73506] update to 3.0.1 of bundle.support (d74005d56b)

### Dependencies
- [LPS-73383] Update the com.liferay.gradle.plugins dependency to version
3.3.35.
- [LPS-65930] Update the com.liferay.gradle.plugins dependency to version
3.3.34.
- [LPS-73506] Update the com.liferay.portal.tools.bundle.support dependency to
version 3.0.1.

### Description
- [LPS-73056] Add the ability to download the Liferay bundle from
[www.liferay.com](https://www.liferay.com) via authentication token. This
behavior can be enabled by setting the `liferay.workspace.bundle.token.download`
property to `true` in `gradle.properties`.
- [LPS-73248] Use Liferay 7.0.3 GA4 as the default bundle in a workspace.

## 1.4.1 - 2017-06-28

### Commits
- [LPS-73056] Fix console prompt from Gradle (ef6d84489f)
- [LPS-73056] Add support for download token (4d5ce745e0)
- [LPS-73056] Reuse default value constants from portal-tools-bundle-support
(b3cd5ca8ea)
- [LPS-73056] Add Gradle task (b73c845650)
- [LPS-73248] Update everywhere (2cccc23ded)
- [LPS-73248] Update Liferay Workspace to use GA4 (a9de63de87)

### Dependencies
- [LPS-73056] Update the com.liferay.portal.tools.bundle.support dependency to
version 3.0.0.
- [LPS-73289] Update the com.liferay.gradle.plugins dependency to version
3.3.33.
- [LPS-73273] Update the com.liferay.gradle.plugins dependency to version
3.3.32.
- [LPS-72705] Update the com.liferay.gradle.plugins dependency to version
3.3.31.
- [LPS-72705] Update the com.liferay.gradle.plugins dependency to version
3.3.30.
- [LPS-72705] Update the com.liferay.gradle.plugins dependency to version
3.3.29.
- [LPS-72705] Update the com.liferay.gradle.plugins dependency to version
3.3.28.
- [LPS-72875] Update the com.liferay.gradle.plugins dependency to version
3.3.27.
- [LPS-71201] Update the com.liferay.gradle.plugins dependency to version
3.3.26.
- [LPS-72914] Update the com.liferay.gradle.plugins dependency to version
3.3.25.
- [LPS-72465] Update the com.liferay.gradle.plugins dependency to version
3.3.24.
- [LPS-72851] Update the com.liferay.gradle.plugins dependency to version
3.3.23.
- [LPS-72830] Update the com.liferay.gradle.plugins dependency to version
3.3.22.
- [LPS-72705] Update the com.liferay.gradle.plugins dependency to version
3.3.21.
- [LPS-72750] Update the com.liferay.gradle.plugins dependency to version
3.3.20.
- [LPS-71722] Update the com.liferay.gradle.plugins dependency to version
3.3.19.
- [LPS-71722] Update the com.liferay.gradle.plugins dependency to version
3.3.18.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version
3.3.17.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version
3.3.16.
- [LPS-72572] Update the com.liferay.gradle.plugins dependency to version
3.3.15.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version
3.3.14.
- [LPS-72562] Update the com.liferay.gradle.plugins dependency to version
3.3.13.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version
3.3.12.
- [LPS-72365] Update the com.liferay.gradle.plugins dependency to version
3.3.11.
- [LPS-71164] Update the com.liferay.gradle.plugins dependency to version
3.3.10.

## 1.4.0 - 2017-05-05

### Commits
- [LPS-71724] Add Gradle test (c9d3ca791a)

### Dependencies
- [LPS-72252] Update the com.liferay.gradle.plugins dependency to version 3.3.9.
- [LPS-72340] Update the com.liferay.gradle.plugins dependency to version 3.3.8.
- [LPS-70890] Update the com.liferay.gradle.plugins dependency to version 3.3.7.
- [LPS-71728] Update the com.liferay.gradle.plugins dependency to version 3.3.6.
- [LPS-71164] Update the com.liferay.gradle.plugins dependency to version 3.3.5.
- [LPS-71722] Update the com.liferay.gradle.plugins dependency to version 3.3.4.
- [LPS-72067] Update the com.liferay.gradle.plugins dependency to version 3.3.3.
- [LPS-72030] Update the com.liferay.gradle.plugins dependency to version 3.3.2.
- [LPS-72039] Update the com.liferay.gradle.plugins dependency to version 3.3.1.
- [LPS-71925] Update the com.liferay.gradle.plugins dependency to version 3.3.0.
- [LPS-71686] Update the com.liferay.gradle.plugins dependency to version
3.2.41.
- [LPS-71901] Update the com.liferay.gradle.plugins dependency to version
3.2.40.
- [LPS-71164] Update the com.liferay.gradle.plugins dependency to version
3.2.39.
- [LPS-71826] Update the com.liferay.gradle.plugins dependency to version
3.2.38.
- [LPS-64098] Update the com.liferay.gradle.plugins dependency to version
3.2.37.
- [LPS-71591] Update the com.liferay.gradle.plugins dependency to version
3.2.36.

### Description
- [LPS-71724] Add the ability to download and upgrade the Plugins SDK
directories by executing the `upgradePluginsSDK` task in the root project.
- [LPS-71724] Add the
[Liferay CDN](https://repository-cdn.liferay.com/nexus/content/groups/public)
as the default repository in the root project. This behavior can be disabled by
setting the `liferay.workspace.default.repository.enabled` property to `false`
in `gradle.properties`.

## 1.3.1 - 2017-04-06

### Commits
- [LPS-71724] Deprecate implicit constructor to avoid major version bump
(ac96640a75)
- [LPS-71724] Add default Maven repository to the root project (d628e10635)
- [LPS-71724] Mark as provider type to avoid major version bump (ee32dc2da8)
- [LPS-71724] Add task to download and upgrade Plugins SDK (c0ff24d00d)
- [LPS-67573] Enable semantic versioning check on CI (63d7f4993f)

### Dependencies
- [LPS-71164] Update the com.liferay.gradle.plugins dependency to version
3.2.35.
- [LPS-71164] Update the com.liferay.gradle.plugins dependency to version
3.2.34.
- [LPS-53392] Update the com.liferay.gradle.plugins dependency to version
3.2.33.
- [LPS-53392] Update the com.liferay.gradle.plugins dependency to version
3.2.32.
- [LPS-71164] Update the com.liferay.gradle.plugins dependency to version
3.2.31.
- [LPS-70819] Update the com.liferay.gradle.plugins dependency to version
3.2.30.
- [LPS-71535] Update the com.liferay.gradle.plugins dependency to version
3.2.29.
- [LPS-71164] Update the com.liferay.gradle.plugins dependency to version
3.2.28.
- [LPS-71164] Update the com.liferay.gradle.plugins dependency to version
3.2.27.
- [LPS-71164] Update the com.liferay.gradle.plugins dependency to version
3.2.26.
- [LPS-71376] Update the com.liferay.gradle.plugins dependency to version
3.2.25.
- [LPS-66891] Update the com.liferay.gradle.plugins dependency to version
3.2.24.
- [LPS-66891] Update the com.liferay.gradle.plugins dependency to version
3.2.23.
- [LPS-71303] Update the com.liferay.gradle.plugins dependency to version
3.2.22.
- [LPS-71164] Update the com.liferay.gradle.plugins dependency to version
3.2.21.
- [LPS-71222] Update the com.liferay.gradle.plugins dependency to version
3.2.20.
- [LPS-71201] Update the com.liferay.gradle.plugins dependency to version
3.2.19.
- [LPS-67688] Update the com.liferay.gradle.plugins dependency to version
3.2.18.
- [LPS-70634] Update the com.liferay.gradle.plugins dependency to version
3.2.17.
- [LPS-68405] Update the com.liferay.gradle.plugins dependency to version
3.2.16.
- [LPS-70604] Update the com.liferay.gradle.plugins dependency to version
3.2.15.
- [LPS-70282] Update the com.liferay.gradle.plugins dependency to version
3.2.14.
- [LPS-71005] Update the com.liferay.gradle.plugins dependency to version
3.2.13.
- [LPS-62970] Update the com.liferay.gradle.plugins dependency to version
3.2.12.
- [LPS-63943] Update the com.liferay.gradle.plugins dependency to version
3.2.11.
- [LPS-70929] Update the com.liferay.gradle.plugins dependency to version
3.2.10.

## 1.3.0 - 2017-02-27

### Dependencies
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version 3.2.9.
- [LPS-70819] Update the com.liferay.gradle.plugins dependency to version 3.2.8.
- [LPS-70819] Update the com.liferay.gradle.plugins dependency to version 3.2.7.

### Description
- [LPS-70677] Add the ability to precompile the JSP files of OSGi modules via
the `liferay.workspace.modules.jsp.precompile.enabled` property in
`gradle.properties`.
- [LPS-67573] Move all properties available in the `gradle.liferayWorkspace`
extension object into the public API.

## 1.2.6 - 2017-02-17

### Commits
- [LPS-70677] Configurators are public API, we should export their package
(b52dbcedd9)
- [LPS-70677] Test compiled JSP classes inclusion in "distBundle" (e15ce17a1e)
- [LPS-70677] Include compiled JSP classes in "distBundle" (910f2b77e8)
- [LPS-70677] Reuse variables and logic (3e6e1576f1)
- [LPS-70677] Test JSP precompile (5d59a617d3)
- [LPS-70677] Add option to write compiled JSPs in Tomcat's "work" directory
(910b9222ea)

### Dependencies
- [LPS-70707] Update the com.liferay.gradle.plugins dependency to version 3.2.6.
- [LPS-70677] Update the com.liferay.gradle.plugins dependency to version 3.2.5.
- [LPS-70494] Update the com.liferay.gradle.plugins dependency to version 3.2.4.
- [LPS-69139] Update the com.liferay.gradle.plugins dependency to version 3.2.3.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version 3.2.2.
- [LPS-70584] Update the com.liferay.gradle.plugins dependency to version 3.2.1.
- [LPS-69926] Update the com.liferay.gradle.plugins dependency to version 3.2.0.
- [LPS-69920] Update the com.liferay.gradle.plugins dependency to version 3.1.9.

## 1.2.5 - 2017-02-08

### Dependencies
- [LPS-70486] Update the com.liferay.gradle.plugins dependency to version 3.1.8.
- [LPS-70424] Update the com.liferay.gradle.plugins dependency to version 3.1.7.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version 3.1.6.
- [LPS-69606] Update the com.liferay.gradle.plugins dependency to version 3.1.5.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version 3.1.4.

## 1.2.4 - 2017-01-30

### Commits
- [LPS-70362] Download Liferay bundles via CDN (b3e1f15c0d)
- [LPS-70353] Test "initBundle" directly (c98f7a3d0b)
- [LPS-70353] add gradle test (9dc505d5c4)
- [LPS-70353] add support for new gradle-download-plugin (e4444f70f1)

### Dependencies
- [LPS-70353] Update the gradle-download-task dependency to version 3.2.0.
- [LPS-70335] Update the com.liferay.gradle.plugins dependency to version 3.1.3.

### Description
- [LPS-70362] Use the Liferay CDN to download bundles by default.

## 1.2.3 - 2017-01-27

### Commits
- [LPS-70060] Test plugins with Gradle 3.3 (09bed59a42)
- [LPS-69294] Use externally accessible URLs (1bc9af5a39)
- [LPS-69730] Recommit (a4ace881cf)
- [LPS-69730] Revert (1182979b8c)

### Dependencies
- [LPS-70286] Update the com.liferay.gradle.plugins dependency to version 3.1.2.
- [LPS-70036] Update the com.liferay.gradle.plugins dependency to version 3.1.1.
- [LPS-70092] Update the com.liferay.gradle.plugins dependency to version 3.1.0.
- [LPS-67573] Update the com.liferay.gradle.plugins dependency to version
3.0.71.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
3.0.70.
- [LPS-69706] Update the com.liferay.gradle.plugins dependency to version
3.0.69.
- [LPS-69706] Update the com.liferay.gradle.plugins dependency to version
3.0.68.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version
3.0.67.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version
3.0.65.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version
3.0.64.
- [LPS-69920] Update the com.liferay.gradle.plugins dependency to version
3.0.63.
- [LPS-69453] Update the com.liferay.gradle.plugins dependency to version
3.0.62.
- [LPS-69838] Update the com.liferay.gradle.plugins dependency to version
3.0.61.
- [LPS-69802] Update the com.liferay.gradle.plugins dependency to version
3.0.60.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version
3.0.59.
- [LPS-69730] Update the com.liferay.gradle.plugins dependency to version
3.0.58.
- [LPS-69730] Update the com.liferay.gradle.plugins dependency to version
3.0.57.
- [LPS-69730] Update the com.liferay.gradle.plugins dependency to version
3.0.52.
- [LPS-69730] Update the com.liferay.gradle.plugins dependency to version
3.0.57.
- [LPS-69730] Update the com.liferay.gradle.plugins dependency to version
3.0.54.
- [LPS-69730] Update the com.liferay.gradle.plugins dependency to version
3.0.53.
- [LPS-67688] Update the com.liferay.gradle.plugins dependency to version
3.0.52.
- [LPS-67694] Update the com.liferay.gradle.plugins dependency to version
3.0.51.
- [LPS-69677] Update the com.liferay.gradle.plugins dependency to version
3.0.50.
- [LPS-69501] Update the com.liferay.gradle.plugins dependency to version
3.0.49.
- [LPS-63943] Update the com.liferay.gradle.plugins dependency to version
3.0.48.

## 1.2.2 - 2016-12-06

### Dependencies
- [LPS-69501] Update the com.liferay.gradle.plugins dependency to version
3.0.47.
- [LPS-68289] Update the com.liferay.gradle.plugins dependency to version
3.0.46.
- [LPS-69492] Update the com.liferay.gradle.plugins dependency to version
3.0.45.
- [LPS-69488] Update the com.liferay.gradle.plugins dependency to version
3.0.44.

## 1.2.1 - 2016-11-30

### Commits
- [LPS-69473] Automatically add Maven Central as repository (8b360e8f83)
- [LPS-69473] Reuse method (7be833376e)
- [LPS-69294] Test if Ant plugins are correctly added to the bundle ZIP
(a9b3fe8ffc)
- [LPS-69294] Test if themes are correctly added to the bundle ZIP (bbb3b7d1c2)
- [LPS-69294] Test if OSGi modules are correctly added to the bundle ZIP
(abe915f652)
- [LPS-69294] Reuse task variable (92870f40f5)
- [LPS-69294] "builtBy" is broken, add dependency explicitly (62c03a33f3)
- [LPS-69294] Consistency with WarsProjectConfigurator (1b94801251)
- [LPS-69259] Test plugins with Gradle 3.2.1 (72873ed836)
- [LPS-69259] Test plugins with Gradle 3.2 (dec6105d3d)
- [LPS-68849] Bypass incompatibility with LifecycleBasePlugin (a8d167ac4e)
- [LPS-68849] add new test for compatibility with maven plugin (24a647971b)

### Dependencies
- [LPS-69445] Update the com.liferay.gradle.plugins dependency to version
3.0.43.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version
3.0.42.
- [LPS-69271] Update the com.liferay.gradle.plugins dependency to version
3.0.41.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
3.0.40.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
3.0.39.
- [LPS-69248] Update the com.liferay.gradle.plugins dependency to version
3.0.38.
- [LPS-66762] Update the com.liferay.gradle.plugins dependency to version
3.0.37.
- [LPS-68298] Update the com.liferay.gradle.plugins dependency to version
3.0.36.
- [LPS-68298] Update the com.liferay.gradle.plugins dependency to version
3.0.35.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
3.0.34.
- [LPS-68923] Update the com.liferay.gradle.plugins dependency to version
3.0.33.
- [LPS-69026] Update the com.liferay.gradle.plugins dependency to version
3.0.32.
- [LPS-68923] Update the com.liferay.gradle.plugins dependency to version
3.0.31.
- [LPS-69013] Update the com.liferay.gradle.plugins dependency to version
3.0.30.
- [LPS-69013] Update the com.liferay.gradle.plugins dependency to version
3.0.29.
- [LPS-66222] Update the com.liferay.gradle.plugins dependency to version
3.0.28.
- [LPS-68980] Update the com.liferay.gradle.plugins dependency to version
3.0.27.
- [LPS-68917] Update the com.liferay.gradle.plugins dependency to version
3.0.26.
- [LPS-52675] Update the com.liferay.gradle.plugins dependency to version
3.0.25.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
3.0.24.

### Description
- [LPS-68849] Apply
[`LifecycleBasePlugin`](https://docs.gradle.org/current/javadoc/org/gradle/language/base/plugins/LifecycleBasePlugin.html)
to the root project.
- [LPS-69473] Add the Maven central repository (if enabled) as the first
default repository for module and WAR projects.
- [LPS-69294] Include theme and WAR files inside the archives generated by the
`distBundleTar` and `distBundleZip` tasks.

## 1.2.0 - 2016-10-24

### Description
- [LPS-68293] Add the
[Liferay CDN](https://repository-cdn.liferay.com/nexus/content/groups/public)
as default repository in WAR projects. This behavior can be disabled by setting
the `liferay.workspace.wars.default.repository.enabled` property in
`gradle.properties` to `false`.
- [LPS-68822] Add the ability to specify a root directory for the archives
generated by the `distBundleTar` and `distBundleZip` tasks via the
`liferay.workspace.bundle.dist.root.dir` property in `gradle.properties`.
- [LPS-68849] Preserve last modified times and empty directories in the outputs
of the `distBundleTar`, `distBundleZip`, and `initBundle` tasks.
- [LPS-68849] Fix overwriting bundle files through the `configs` directory.

## 1.1.1 - 2016-10-24

### Commits
- [LPS-68849] Test "distBundleZip" task (8806d8cf40)
- [LPS-68849] Test "initBundle" task (985e609640)
- [LPS-68849] Disable up-to-date check for "distBundle*" tasks (3986049ab0)
- [LPS-68822] Allow to choose a root dir for the bundle dist archives
(bec6d7127d)
- [LPS-68822] Extract method (ee95857c95)
- [LPS-68849] Always use the internal util class (723eb1da73)
- [LPS-68849] Add intermediate "distBundle" task to fix ZIP and TAR (d39f1e8296)
- [LPS-68849] Bypass GRADLE-2698 and preserve timestamps in "initBundle"
(4de0c41576)
- [LPS-68849] Allow to overwrite bundle files via the "config" dir (7dc0de964d)
- [LPS-68361] Remove gradle-plugins-workspace samples, it's an archetype now
(daa7b111e1)
- [LPS-68293] Update samples (bc0fdbfc09)
- [LPS-68293] Option to add Liferay CDN as the default repository (85760416a0)

### Dependencies
- [LPS-68917] Update the com.liferay.gradle.plugins dependency to version
3.0.23.
- [LPS-66906] Update the com.liferay.gradle.plugins dependency to version
3.0.22.
- [LPS-68838] Update the com.liferay.gradle.plugins dependency to version
3.0.21.
- [LPS-68839] Update the com.liferay.gradle.plugins dependency to version
3.0.20.
- [LPS-67434] Update the com.liferay.gradle.plugins dependency to version
3.0.19.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
3.0.18.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
3.0.17.
- [LPS-68779] Update the com.liferay.gradle.plugins dependency to version
3.0.16.
- [LPS-68817] Update the com.liferay.gradle.plugins dependency to version
3.0.15.
- [LPS-68779] Update the com.liferay.gradle.plugins dependency to version
3.0.14.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
3.0.13.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
3.0.12.
- [LRDOCS-3038] Update the com.liferay.gradle.plugins dependency to version
3.0.11.
- [LPS-68666] Update the com.liferay.gradle.plugins dependency to version
3.0.10.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version 3.0.9.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version 3.0.8.
- [LPS-68598] Update the com.liferay.gradle.plugins dependency to version 3.0.7.
- [LPS-68618] Update the com.liferay.gradle.plugins dependency to version 3.0.6.
- [LRDOCS-2594] Update the com.liferay.gradle.plugins dependency to version
3.0.5.
- [LRDOCS-3023] Update the com.liferay.gradle.plugins dependency to version
3.0.4.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version 3.0.3.
- [LPS-68564] Update the com.liferay.gradle.plugins dependency to version 3.0.2.
- [LPS-68415] Update the com.liferay.gradle.plugins dependency to version 3.0.1.
- [LPS-67573] Update the com.liferay.gradle.plugins dependency to version 3.0.0.
- [LPS-68334] Update the com.liferay.gradle.plugins dependency to version
2.0.50.
- [LPS-68506] Update the com.liferay.gradle.plugins dependency to version
2.0.49.
- [LPS-68485] Update the com.liferay.gradle.plugins dependency to version
2.0.48.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version
2.0.47.
- [LPS-58672] Update the com.liferay.gradle.plugins dependency to version
2.0.46.

## 1.1.0 - 2016-09-28

### Commits
- [LPS-68293] Update samples (a9116ad107)

### Description
- [LPS-68293] Add support for WAR projects, contained in the `wars` directory
of a Liferay Workspace. For each WAR project, the following configuration is
applied:
	- applies the
	[`war`](https://docs.gradle.org/current/userguide/war_plugin.html)
	plugin
	- adds a `deploy` task
	- configures the `distBundleTar` and `distBundleZip` task to save the
	generated WAR file in the `osgi/war` directory of the bundle
- [LPS-67573] Make most methods private in order to reduce API surface.

## 1.0.42 - 2016-09-28

### Commits
- [LPS-68293] Configure WAR projects (2fa99e571e)
- [LPS-67573] Avoid leaking an internal impl (09c5cd41b3)
- [LPS-68293] Export interface (fd5bbe1511)
- [LPS-67573] Export packages (aed6b40188)
- [LPS-67573] Remove unused parameter (6b5ca26a02)
- [LPS-67573] Make methods private to reduce API surface (d287fb65c6)
- [LPS-67573] Move internal classes to their own packages (83cd60bc77)

### Dependencies
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version
2.0.45.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
2.0.44.

## 1.0.41 - 2016-09-26

### Commits
- [LPS-67656] Hugo SF (5c1002798b)
- [LPS-67656] Fix compile (8a212eaa07)
- [LPS-67656] Update sample gradle properties (34c9e9f7c1)
- [LPS-67656] Update workspace to use GA3 (a700a2cb81)
- [LPS-67352] SF, enforce empty line after finishing referencing variable
(4ff2bb6038)
- [LPS-66853] cache (1b8dfc1c29)

### Dependencies
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
2.0.42.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
2.0.41.
- [LPS-66906] Update the com.liferay.gradle.plugins dependency to version
2.0.40.
- [LPS-68297] Update the com.liferay.gradle.plugins dependency to version
2.0.39.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
2.0.38.
- [LPS-67653] Update the com.liferay.gradle.plugins dependency to version
2.0.37.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version
2.0.36.
- [LPS-68131] Update the com.liferay.gradle.plugins dependency to version
2.0.35.
- [LPS-68131] Update the com.liferay.gradle.plugins dependency to version
2.0.34.
- [LPS-67986] Update the com.liferay.gradle.plugins dependency to version
2.0.33.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version
2.0.32.
- [LPS-67766] Update the com.liferay.gradle.plugins dependency to version
2.0.31.
- [LRDOCS-2841] Update the com.liferay.gradle.plugins dependency to version
2.0.30.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
2.0.29.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
2.0.28.
- [LPS-68035] Update the com.liferay.gradle.plugins dependency to version
2.0.27.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
2.0.26.
- [LPS-67996] Update the com.liferay.gradle.plugins dependency to version
2.0.25.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
2.0.24.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
2.0.23.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
2.0.22.
- [LPS-67986] Update the com.liferay.gradle.plugins dependency to version
2.0.21.

### Description
- [LPS-67656] Use Liferay 7.0.2 GA3 as the default bundle in a workspace.

## 1.0.40 - 2016-09-01

### Commits
- [LPS-67952] (dd79f08fe5)
- [LPS-67658] Need "compileOnly" to keep dependencies out of "compile"
(4a3cd0bc9d)
- [LPS-67658] These plugins must work with Gradle 2.14+ (8cf811e5af)
- [LPS-67544] Fix compile (6fb71c6e53)

### Dependencies
- [LPS-67952] Update the com.liferay.gradle.plugins dependency to version
2.0.10.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version
2.0.20.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
2.0.19.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
2.0.18.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version
2.0.17.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
2.0.16.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
2.0.15.
- [LPS-67023] Update the com.liferay.gradle.plugins dependency to version
2.0.14.
- [LPS-67804] Update the com.liferay.gradle.plugins dependency to version
2.0.13.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version
2.0.12.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version
2.0.11.
- [LPS-67658] Update the com.liferay.gradle.plugins dependency to version
2.0.10.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version 2.0.9.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version 2.0.8.
- [LPS-67596] Update the com.liferay.gradle.plugins dependency to version 2.0.7.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version 2.0.6.
- [LPS-66906] Update the com.liferay.gradle.plugins dependency to version 2.0.5.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version 2.0.4.
- [LPS-65786] Update the com.liferay.gradle.plugins dependency to version 2.0.3.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version 2.0.2.
- [LPS-67544] Update the com.liferay.gradle.plugins dependency to version 2.0.1.
- [LPS-67544] Update the com.liferay.gradle.plugins dependency to version 2.0.0.
- [LRDOCS-2841] Update the com.liferay.gradle.plugins dependency to version
1.0.412.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version
1.0.411.
- [LPS-66906] Update the com.liferay.gradle.plugins dependency to version
1.0.410.
- [LPS-67352] Update the com.liferay.gradle.plugins dependency to version
1.0.409.
- [LPS-67503] Update the com.liferay.gradle.plugins dependency to version
1.0.408.
- [LPS-67483] Update the com.liferay.gradle.plugins dependency to version
1.0.407.

## 1.0.39 - 2016-08-05

### Dependencies
- [LPS-66906] Update the com.liferay.gradle.plugins dependency to version
1.0.406.

## 1.0.38 - 2016-08-05

### Dependencies
- [LPS-67495] Update the com.liferay.gradle.plugins dependency to version
1.0.405.
- [LPS-67434] Update the com.liferay.gradle.plugins dependency to version
1.0.404.
- [LPS-67111] Update the com.liferay.gradle.plugins dependency to version
1.0.403.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
1.0.402.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
1.0.401.
- [LPS-66906] Update the com.liferay.gradle.plugins dependency to version
1.0.400.

## 1.0.37 - 2016-07-28

### Commits
- [LPS-65749] Update Gradle wrapper in gradle-plugins-workspace (4f3afa1645)

### Dependencies
- [LPS-65749] Update the com.liferay.gradle.plugins dependency to version
1.0.399.
- [LPS-67029] Update the com.liferay.gradle.plugins dependency to version
1.0.398.
- [LPS-67314] Update the gradle-download-task dependency to version 3.1.1.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
1.0.397.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
1.0.396.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
1.0.395.
- [LPS-67151] Update the com.liferay.gradle.plugins dependency to version
1.0.394.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
1.0.393.
- [LPS-65920] Update the com.liferay.gradle.plugins dependency to version
1.0.392.
- [LPS-67190] Update the com.liferay.gradle.plugins dependency to version
1.0.391.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
1.0.390.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
1.0.389.
- [LPS-67111] Update the com.liferay.gradle.plugins dependency to version
1.0.388.
- [LPS-67042] Update the com.liferay.gradle.plugins dependency to version
1.0.387.
- [LPS-67042] Update the com.liferay.gradle.plugins dependency to version
1.0.386.
- [LPS-66797] Update the com.liferay.gradle.plugins dependency to version
1.0.385.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
1.0.384.

## 1.0.36 - 2016-07-11

### Commits
- [LPS-66883] Replace spaces in the bundle URL (7a21fe08f7)

### Dependencies
- [LPS-66883] Update the gradle-download-task dependency to version 3.1.0.
- [LPS-67048] Update the com.liferay.gradle.plugins dependency to version
1.0.383.
- [LPS-66853 LPS-63943] Update the com.liferay.gradle.plugins dependency to
version 1.0.382.
- [LPS-67029] Update the com.liferay.gradle.plugins dependency to version
1.0.381.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
1.0.380.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
1.0.379.
- [LPS-66731] Update the com.liferay.gradle.plugins dependency to version
1.0.378.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
1.0.377.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
1.0.376.
- [LPS-65976] Update the com.liferay.gradle.plugins dependency to version
1.0.375.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
1.0.374.
- [LPS-66951] Update the com.liferay.gradle.plugins dependency to version
1.0.373.
- [LRQA-25540] Update the com.liferay.gradle.plugins dependency to version
1.0.372.
- [LPS-66941] Update the com.liferay.gradle.plugins dependency to version
1.0.371.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
1.0.370.

## 1.0.35 - 2016-06-29

### Commits
- [LPS-66860] Use Gradle wrapper 2.14 in Liferay Workspace (14c7968164)

### Dependencies
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
1.0.369.
- [LPS-66853] Update the com.liferay.gradle.plugins dependency to version
1.0.368.
- [LRQA-25824] Update the com.liferay.gradle.plugins dependency to version
1.0.367.
- [LPS-66709] Update the com.liferay.gradle.plugins dependency to version
1.0.366.

## 1.0.34 - 2016-06-17

### Commits
- [LPS-66698] Update sample gradle.properties (3cdc909b39)
- [LPS-65749] Use reflection to support multiple versions of Gradle (4ca8db28c1)
- [LPS-66698] Update workspace to use GA2 (741fd95fd2)

## 1.0.33 - 2016-06-16

### Commits
- [LPS-65749] Closures with null owners don't work in Gradle 2.14 (b42316699d)

### Dependencies
- [LPS-65749] Update the com.liferay.gradle.plugins dependency to version
1.0.365.
- [LPS-65749] Update the com.liferay.gradle.plugins.poshi.runner dependency to
version 1.0.11.
- [LPS-66663] Update the com.liferay.gradle.plugins dependency to version
1.0.364.
- [LPS-66663] Update the com.liferay.gradle.plugins dependency to version
1.0.363.

## 1.0.32 - 2016-06-14

### Dependencies
- [LPS-66639] Update the com.liferay.gradle.plugins dependency to version
1.0.362.
- [LPS-66595] Update the com.liferay.gradle.plugins dependency to version
1.0.361.
- [LPS-55111] Update the com.liferay.gradle.plugins dependency to version
1.0.360.
- [LPS-61099] Update the com.liferay.gradle.plugins dependency to version
1.0.359.
- [LPS-61099] Update the com.liferay.gradle.plugins dependency to version
1.0.358.
- [LPS-61099] Update the com.liferay.gradle.plugins dependency to version
1.0.357.
- [LPS-64619] Update the com.liferay.gradle.plugins dependency to version
1.0.356.
- [LPS-63943] Update the com.liferay.gradle.plugins dependency to version
1.0.355.
- [LPS-66410] Update the com.liferay.gradle.plugins dependency to version
1.0.354.

## 1.0.31 - 2016-06-08

### Commits
- [LPS-66473] Automatically apply the Service Builder plugin (fe0f0e63ef)
- [LPS-66473] Extract method (73826848b8)

### Dependencies
- [LPS-66413] Update the com.liferay.gradle.plugins dependency to version
1.0.353.

## 1.0.30 - 2016-06-07

### Commits
- [LPS-66431] Support multiple root directories (e6bd1dbe86)
- [LPS-65179] Leverage LiferayThemePlugin (0345b4daab)
- [LPS-65179] Apply the OSGi plugin directly (11366abfa9)
- [LPS-65810] Gradle plugins aren't used in OSGi, no need to export anything
(83cdd8ddcd)
- [LPS-64816] Update Gradle plugin samples (3331002e5d)

### Dependencies
- [LPS-66410] Update the com.liferay.gradle.plugins dependency to version
1.0.352.
- [LPS-66050] Update the com.liferay.gradle.plugins dependency to version
1.0.351.
- [LPS-64755] Update the com.liferay.gradle.plugins dependency to version
1.0.350.
- [LPS-66281] Update the com.liferay.gradle.plugins dependency to version
1.0.349.
- [LPS-63943] Update the com.liferay.gradle.plugins dependency to version
1.0.348.
- [LPS-63943] Update the com.liferay.gradle.plugins dependency to version
1.0.347.
- [LPS-66282] Update the com.liferay.gradle.plugins dependency to version
1.0.346.
- [LPS-66324] Update the com.liferay.gradle.plugins dependency to version
1.0.345.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.344.
- [LPS-63943] Update the com.liferay.gradle.plugins dependency to version
1.0.343.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.342.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.341.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.340.
- [LPS-66242] Update the com.liferay.gradle.plugins dependency to version
1.0.339.
- [LPS-66099] Update the com.liferay.gradle.plugins dependency to version
1.0.338.
- [LPS-63943] Update the com.liferay.gradle.plugins dependency to version
1.0.337.
- [LPS-63943] Update the com.liferay.gradle.plugins dependency to version
1.0.336.
- [LPS-66061] Update the com.liferay.gradle.plugins dependency to version
1.0.335.
- [LPS-66061] Update the com.liferay.gradle.plugins dependency to version
1.0.334.
- [LPS-66061] Update the com.liferay.gradle.plugins dependency to version
1.0.333.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.332.
- [LPS-66099] Update the com.liferay.gradle.plugins dependency to version
1.0.331.
- [LPS-65693] Update the com.liferay.gradle.plugins dependency to version
1.0.330.
- [LPS-66139] Update the com.liferay.gradle.plugins dependency to version
1.0.329.
- [LPS-66064] Update the com.liferay.gradle.plugins dependency to version
1.0.328.
- [LPS-64021] Update the com.liferay.gradle.plugins dependency to version
1.0.327.
- [LRDOCS-2547] Update the com.liferay.gradle.plugins dependency to version
1.0.326.
- [LRDOCS-2547 LPS-65119] Update the com.liferay.gradle.plugins dependency to
version 1.0.325.
- [LPS-65810] Update the com.liferay.gradle.plugins dependency to version
1.0.324.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.323.
- [LPS-65845] Update the com.liferay.gradle.plugins dependency to version
1.0.322.
- [LPS-65971] Update the com.liferay.gradle.plugins dependency to version
1.0.321.
- [LPS-64031] Update the com.liferay.gradle.plugins dependency to version
1.0.320.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.319.
- [LPS-65685] Update the com.liferay.gradle.plugins dependency to version
1.0.318.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.317.
- [LPS-65316] Update the com.liferay.gradle.plugins dependency to version
1.0.316.
- [LPS-65232] Update the com.liferay.gradle.plugins dependency to version
1.0.315.
- [LPS-65568] Update the com.liferay.gradle.plugins dependency to version
1.0.314.
- [LPS-65810] Update the com.liferay.gradle.plugins dependency to version
1.0.313.
- [LPS-65716] Update the com.liferay.gradle.plugins dependency to version
1.0.312.
- [LPS-65845] Update the com.liferay.gradle.plugins dependency to version
1.0.311.
- [LPS-65636] Update the com.liferay.gradle.plugins dependency to version
1.0.310.
- [LPS-65799] Update the com.liferay.gradle.plugins dependency to version
1.0.309.
- [LPS-65179] Update the com.liferay.gradle.plugins dependency to version
1.0.308.
- [LPS-65799] Update the com.liferay.gradle.plugins dependency to version
1.0.307.
- [LPS-64186] Update the com.liferay.gradle.plugins dependency to version
1.0.306.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.305.
- [LPS-65799] Update the com.liferay.gradle.plugins dependency to version
1.0.304.
- [LPS-65788] Update the com.liferay.gradle.plugins dependency to version
1.0.303.
- [LPS-64991] Update the com.liferay.gradle.plugins dependency to version
1.0.302.
- [LPS-65753] Update the com.liferay.gradle.plugins dependency to version
1.0.301.
- [LPS-65741] Update the com.liferay.gradle.plugins dependency to version
1.0.300.
- [LPS-65383] Update the com.liferay.gradle.plugins dependency to version
1.0.299.
- [LPS-65362] Update the com.liferay.gradle.plugins dependency to version
1.0.298.
- [LPS-65716] Update the com.liferay.gradle.plugins dependency to version
1.0.297.
- [LPS-65633] Update the com.liferay.gradle.plugins dependency to version
1.0.296.
- [LPS-65636] Update the com.liferay.gradle.plugins dependency to version
1.0.295.
- [LPS-65528] Update the com.liferay.gradle.plugins dependency to version
1.0.294.
- [LPS-64991] Update the com.liferay.gradle.plugins dependency to version
1.0.293.
- [LPS-65528] Update the com.liferay.gradle.plugins dependency to version
1.0.292.
- [LPS-62570] Update the com.liferay.gradle.plugins dependency to version
1.0.291.
- [LPS-65490] Update the com.liferay.gradle.plugins dependency to version
1.0.290.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.289.
- [LPS-65338] Update the com.liferay.gradle.plugins dependency to version
1.0.288.
- [LPS-65323] Update the com.liferay.gradle.plugins dependency to version
1.0.287.
- [LPS-65323] Update the com.liferay.gradle.plugins dependency to version
1.0.285.
- [LPS-65387] Update the com.liferay.gradle.plugins dependency to version
1.0.284.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.283.
- [LPS-65387] Update the com.liferay.gradle.plugins dependency to version
1.0.282.
- [LPS-65179] Update the com.liferay.gradle.plugins dependency to version
1.0.281.
- [LPS-65179] Update the com.liferay.gradle.plugins dependency to version
1.0.280.
- [LPS-62570] Update the com.liferay.gradle.plugins dependency to version
1.0.279.
- [LPS-64991] Update the com.liferay.gradle.plugins dependency to version
1.0.278.
- [LPS-64991] Update the com.liferay.gradle.plugins dependency to version
1.0.277.
- [LPS-64991] Update the com.liferay.gradle.plugins dependency to version
1.0.276.
- [LPS-63740] Update the com.liferay.gradle.plugins dependency to version
1.0.275.

## 1.0.29 - 2016-04-24

### Commits
- [LPS-61420] Republish (14387e02e6)

### Dependencies
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.274.
- [LPS-65283] Update the com.liferay.gradle.plugins dependency to version
1.0.273.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.272.

## 1.0.28 - 2016-04-21

### Commits
- [LPS-65179] Fix compile (7a04fe150d)
- [LPS-65179] This is already a transitive dependency now (3b2bfdf86c)

### Dependencies
- [LPS-65245] Update the com.liferay.gradle.plugins dependency to version
1.0.271.
- [LPS-65220] Update the com.liferay.gradle.plugins dependency to version
1.0.270.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.269.
- [LPS-65179] Update the com.liferay.gradle.plugins dependency to version
1.0.268.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.267.

## 1.0.27 - 2016-04-18

### Dependencies
- [LPS-65135] Update the com.liferay.gradle.plugins dependency to version
1.0.266.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.265.
- [LPS-65086] Update the com.liferay.gradle.plugins dependency to version
1.0.264.

## 1.0.26 - 2016-04-15

### Dependencies
- [LPS-63943] Update the com.liferay.gradle.plugins dependency to version
1.0.263.
- [LPS-65072] Update the com.liferay.gradle.plugins dependency to version
1.0.262.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.261.
- [LPS-65094] Update the com.liferay.gradle.plugins dependency to version
1.0.260.
- [LPS-61099] Update the com.liferay.gradle.plugins dependency to version
1.0.259.
- [LPS-64713] Update the com.liferay.gradle.plugins dependency to version
1.0.258.
- [LPS-65064] Update the com.liferay.gradle.plugins dependency to version
1.0.257.
- [LPS-64021] Update the com.liferay.gradle.plugins dependency to version
1.0.256.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.255.
- [LPS-64875] Update the com.liferay.gradle.plugins dependency to version
1.0.254.

## 1.0.25 - 2016-04-11

### Commits
- [LPS-65000] Deploy module before running functional test (affe576b19)
- [LPS-61099] Delete build.xml in modules (c9a7e1d370)

### Dependencies
- [LPS-64875] Update the com.liferay.gradle.plugins dependency to version
1.0.253.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.252.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.251.
- [LPS-61443] Update the com.liferay.gradle.plugins dependency to version
1.0.250.
- [LPS-61099] Update the com.liferay.gradle.plugins dependency to version
1.0.249.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.248.
- [LPS-62989] Update the com.liferay.gradle.plugins dependency to version
1.0.247.
- [LPS-64897] Update the com.liferay.gradle.plugins dependency to version
1.0.246.
- [LPS-64847] Update the com.liferay.gradle.plugins dependency to version
1.0.245.
- [LPS-61099] Update the com.liferay.gradle.plugins dependency to version
1.0.244.
- [LPS-60972] Update the com.liferay.gradle.plugins dependency to version
1.0.243.

## 1.0.24 - 2016-04-04

### Commits
- [LPS-61767] update to GA1 (f6c8a52efc)

### Dependencies
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.242.

## 1.0.23 - 2016-03-31

### Commits
- [LPS-61767] Don't set task descriptions if someone already entered them
(fc35ac8f87)
- [LPS-61767] Defer evaluation of the downloadBundle.src property (969fdf94ee)

### Dependencies
- [LPS-64786] Update the com.liferay.gradle.plugins dependency to version
1.0.241.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.240.
- [LPS-64761] Update the com.liferay.gradle.plugins dependency to version
1.0.239.
- [LPS-62883] Update the com.liferay.gradle.plugins dependency to version
1.0.238.
- [LPS-64383] Update the com.liferay.gradle.plugins dependency to version
1.0.237.
- [LPS-64719] Update the com.liferay.gradle.plugins dependency to version
1.0.236.
- [LPS-63162] Update the com.liferay.gradle.plugins dependency to version
1.0.235.

## 1.0.22 - 2016-03-28

### Dependencies
- [LPS-64191] Update the com.liferay.gradle.plugins dependency to version
1.0.234.

## 1.0.21 - 2016-03-28

### Commits
- [LPS-61767] Avoid downloading the zip every time (d2303f30ca)
- [LPS-61767] Update sample (b656a596f6)
- [LPS-61767] This method is only used once (bde3cf4aa6)
- [LPS-61767] Download Liferay bundle from SourceForge (cd8859bb7e)
- [LPS-61767] Update Gradle wrapper (83d87bf617)

### Dependencies
- [LPS-61767] Update the gradle-download-task dependency to version 2.1.0.

## 1.0.20 - 2016-03-28

### Commits
- [LPS-62986] Apply PoshiRunnerPlugin (fe39d8bc78)
- [LPS-61767] update defaults to rc1-20160325160942584 (4bdc5e64c0)
- [LPS-64309] revert usage (90ce0aaef7)
- [LPS-64076] gradle-plugins 1.0.193 (932ec9005e)

### Dependencies
- [LPS-62986] Update the com.liferay.gradle.plugins.poshi.runner dependency to
version 1.0.10.
- [LPS-62986] Update the com.liferay.gradle.plugins.poshi.runner dependency to
version 1.0.9.
- [LPS-64668] Update the com.liferay.gradle.plugins dependency to version
1.0.233.
- [LPS-64654 LPS-64619] Update the com.liferay.gradle.plugins dependency to
version 1.0.232.
- [LPS-64619] Update the com.liferay.gradle.plugins dependency to version
1.0.231.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.230.
- [LPS-64586] Update the com.liferay.gradle.plugins dependency to version
1.0.229.
- [LPS-64596] Update the com.liferay.gradle.plugins dependency to version
1.0.228.
- [LPS-64343] Update the com.liferay.gradle.plugins dependency to version
1.0.227.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.226.
- [LPS-64532] Update the com.liferay.gradle.plugins dependency to version
1.0.225.
- [LPS-62493] Update the com.liferay.gradle.plugins dependency to version
1.0.224.
- [LPS-64407] Update the com.liferay.gradle.plugins dependency to version
1.0.223.
- [LPS-63943] Update the com.liferay.gradle.plugins dependency to version
1.0.222.
- [LPS-63943] Update the com.liferay.gradle.plugins dependency to version
1.0.221.
- [LPS-61720] Update the com.liferay.gradle.plugins dependency to version
1.0.220.
- [LPS-63943] Update the com.liferay.gradle.plugins dependency to version
1.0.219.
- [LPS-63943] Update the com.liferay.gradle.plugins dependency to version
1.0.218.
- [LPS-63943] Update the com.liferay.gradle.plugins dependency to version
1.0.217.
- [LPS-63943] Update the com.liferay.gradle.plugins dependency to version
1.0.216.
- [LPS-64376] Update the com.liferay.gradle.plugins dependency to version
1.0.215.
- [LPS-63943] Update the com.liferay.gradle.plugins dependency to version
1.0.214.
- [LPS-64281] Update the com.liferay.gradle.plugins dependency to version
1.0.213.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.212.
- [LPS-63943] Update the com.liferay.gradle.plugins dependency to version
1.0.211.
- [LPS-64343] Update the com.liferay.gradle.plugins dependency to version
1.0.210.
- [LPS-64092] Update the com.liferay.gradle.plugins dependency to version
1.0.209.
- [LPS-64031] Update the com.liferay.gradle.plugins dependency to version
1.0.208.
- [LPS-63493] Update the com.liferay.gradle.plugins dependency to version
1.0.207.
- [LPS-64309] Update the com.liferay.gradle.plugins dependency to version
1.0.206.
- [LPS-64309] Update the com.liferay.gradle.plugins dependency to version
1.0.205.
- [LPS-64309] Update the com.liferay.gradle.plugins dependency to version
1.0.203.
- [LPS-64309] Update the com.liferay.gradle.plugins dependency to version
1.0.204.
- [LPS-63943] Update the com.liferay.gradle.plugins dependency to version
1.0.203.
- [LPS-64191] Update the com.liferay.gradle.plugins dependency to version
1.0.202.
- [LPS-64191] Update the com.liferay.gradle.plugins dependency to version
1.0.201.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.200.
- [LPS-64021] Update the com.liferay.gradle.plugins dependency to version
1.0.199.
- [LPS-64021] Update the com.liferay.gradle.plugins dependency to version
1.0.198.
- [LPS-64188] Update the com.liferay.gradle.plugins dependency to version
1.0.197.
- [LPS-64182] Update the com.liferay.gradle.plugins dependency to version
1.0.196.
- [LPS-63855] Update the com.liferay.gradle.plugins dependency to version
1.0.195.
- [LPS-62450] Update the com.liferay.gradle.plugins dependency to version
1.0.194.
- [LPS-64076] Update the com.liferay.gradle.plugins dependency to version
1.0.193.
- [LPS-64076] Update the com.liferay.gradle.plugins dependency to version
1.0.192.
- [LPS-64021] Update the com.liferay.gradle.plugins dependency to version
1.0.191.
- [LPS-63943] Update the com.liferay.gradle.plugins dependency to version
1.0.182.
- [LPS-64021] Update the com.liferay.gradle.plugins dependency to version
1.0.181.
- [LPS-63943] Update the com.liferay.gradle.plugins dependency to version
1.0.180.
- [LPS-63462] Update the com.liferay.gradle.plugins dependency to version
1.0.179.

## 1.0.19 - 2016-03-08

### Dependencies
- [LPS-64029] Update the com.liferay.gradle.plugins dependency to version
1.0.178.
- [LPS-64002] Update the com.liferay.gradle.plugins dependency to version
1.0.177.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.176.
- [LPS-63943] Update the com.liferay.gradle.plugins dependency to version
1.0.175.
- [LPS-63499] Update the com.liferay.gradle.plugins dependency to version
1.0.174.
- [LPS-63943] Update the com.liferay.gradle.plugins dependency to version
1.0.173.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.172.
- [LPS-62450] Update the com.liferay.gradle.plugins dependency to version
1.0.171.

## 1.0.18 - 2016-03-06

### Commits
- [LPS-61420] modules/sdk/gradle-plugins-workspace/bnd.bnd (4da3c177c8)

### Dependencies
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.170.
- [LPS-63943] Update the com.liferay.gradle.plugins dependency to version
1.0.169.
- [LPS-63943] Update the com.liferay.gradle.plugins dependency to version
1.0.168.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.167.

## 1.0.17 - 2016-03-03

### Dependencies
- [LPS-63864] Update the com.liferay.gradle.plugins dependency to version
1.0.166.

## 1.0.16 - 2016-03-03

### Dependencies
- [LPS-63792] Update the com.liferay.gradle.plugins dependency to version
1.0.165.

## 1.0.15 - 2016-03-02

### Dependencies
- [LPS-62485] Update the com.liferay.gradle.plugins dependency to version
1.0.164.
- [LPS-63734] Update the com.liferay.gradle.plugins dependency to version
1.0.163.

## 1.0.14 - 2016-03-02

### Dependencies
- [LPS-63857] Update the com.liferay.gradle.plugins dependency to version
1.0.162.
- [LPS-63462] Update the com.liferay.gradle.plugins dependency to version
1.0.161.
- [LPS-63462] Update the com.liferay.gradle.plugins dependency to version
1.0.160.
- [LPS-63797] Update the com.liferay.gradle.plugins dependency to version
1.0.159.

## 1.0.13 - 2016-03-01

### Commits
- [LPS-63791] Update Tomcat version (7fcbf09dfb)

### Dependencies
- [LPS-63791] Update the com.liferay.gradle.plugins dependency to version
1.0.158.
- [LPS-63706] Update the com.liferay.gradle.plugins dependency to version
1.0.157.
- [LPS-63706] Update the com.liferay.gradle.plugins dependency to version
1.0.156.
- [LPS-63558] Update the com.liferay.gradle.plugins dependency to version
1.0.155.
- [LPS-63706] Update the com.liferay.gradle.plugins dependency to version
1.0.154.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.153.
- [LPS-63112] Update the com.liferay.gradle.plugins dependency to version
1.0.152.
- [LPS-63631] Update the com.liferay.gradle.plugins dependency to version
1.0.151.
- [LPS-63470] Update the com.liferay.gradle.plugins dependency to version
1.0.150.

## 1.0.12 - 2016-02-23

### Commits
- [LPS-63410] "assemble" depends on the tasks that build the artifacts
(ec039ed5db)
- [LPS-63410] Add task description (2b72446a95)
- [LPS-63410] "gulpBuild" is enough to generate the war file in "dist"
(d40714df48)
- [LPS-63410] Consistency (7120ca9b64)
- [LPS-63410] Fix archives artifact configuration (02ed847438)
- [LPS-63410] Add BuiltBy dependency (a96346b02f)
- [LPS-63410] add output war file to archives configurations (8386d8fbeb)

### Dependencies
- [LPS-63525] Update the com.liferay.gradle.plugins dependency to version
1.0.149.

## 1.0.11 - 2016-02-19

### Commits
- [LPS-61952] (6ce5b94219)
- [LPS-61952] (ac5c73f199)

### Dependencies
- [LPS-61952] Update the com.liferay.gradle.plugins dependency to version
1.0.148.

## 1.0.10 - 2016-02-17

### Dependencies
- [LPS-63182] Update the com.liferay.gradle.plugins dependency to version
1.0.147.
- [LPS-63309] Update the com.liferay.gradle.plugins dependency to version
1.0.146.

## 1.0.9 - 2016-02-16

### Dependencies
- [LPS-63161] Update the com.liferay.gradle.plugins dependency to version
1.0.145.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.144.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.143.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.142.
- [LPS-62741] Update the com.liferay.gradle.plugins dependency to version
1.0.141.
- [LPS-62570] Update the com.liferay.gradle.plugins dependency to version
1.0.140.
- [LPS-62570] Update the com.liferay.gradle.plugins dependency to version
1.0.139.
- [LPS-61420] Update the com.liferay.gradle.plugins dependency to version
1.0.138.
- [LPS-61952] Update the com.liferay.gradle.plugins dependency to version
1.0.137.

## 1.0.8 - 2016-02-08

### Commits
- [LPS-55250] If the "configs" dir doesn't exist, "initBundle" does not work
(f6ff56dcc0)
- [LPS-55250] Update Tomcat version (0e3606ff8e)
- [LPS-62942] Explicitly list exported packages for correctness (f095a51e25)

### Dependencies
- [LPS-55250] Update the com.liferay.gradle.plugins dependency to version
1.0.135.

## 1.0.7 - 2016-01-26

### Dependencies
- [LPS-62504] Update the com.liferay.gradle.plugins dependency to version
1.0.101.
- [LPS-62504] Update the com.liferay.gradle.plugins.gulp dependency to version
1.0.1.

## 1.0.6 - 2016-01-22

### Commits
- [LPS-61767] Group root project tasks (3218cfaa91)
- [LPS-61767] Task description (copied from Gradle's LifecycleBasePlugin)
(a97daa2a50)
- [LPS-61767] Create a Delete task instead (289c5bdee9)
- [LPS-61767] we should always create the json file even if its missing
(f0258f47a5)
- [LPS-61767] delete root build dir on clean which will clean the distBundle
archive (ed86105f74)
- [LPS-61088] Remove classes and resources dir from Include-Resource
(1b0e1275bc)
- [LPS-61767] Add task description (3be35165d5)
- [LPS-61767] Add task description (copied from LiferayJavaPlugin) (ce8e04c99f)
- [LPS-61767] Rename, we're creating a task here (b517946c67)
- [LPS-61767] Fix when a root dir does not exist (eg. "modules" is missing)
(8aae047729)
- [LPS-61767] Copy artifacts to "osgi/modules" in the dist bundle (640907b862)
- [LPS-61767] Deploy themes to "osgi/modules" (62fb2e4e4c)
- [LPS-61767] Remove sample module directories (a69670117c)
- [LPS-61767] use full English words for logs (8e30dc08bc)

### Dependencies
- [LPS-61767] Update the com.liferay.gradle.plugins dependency to version
1.0.95.
- [LPS-61767] Update the com.liferay.gradle.plugins dependency to version
1.0.92.

## 1.0.5 - 2016-01-19

### Commits
- [LPS-61767] Readd empty build.gradle file as placeholder (e85b37269b)
- [LPS-61767] Update sample (ee3e74d1bd)
- [LPS-61767] Those are already the default values (770488340f)
- [LPS-61767] Convert to a "settings" plugin, this way we can remove everything
from settings.gradle (b4be4c57fd)
- [LPS-61767] Extension properties default values (ff8db6d22d)
- [LPS-61767] Use local util class (71e694e31a)
- [LPS-61767] Add setters for extension properties (a3df712bff)
- [LPS-61767] Isolate plugins SDK configuration (25e3f99ac5)
- [LPS-61767] Add comment (3f95254f37)
- [LPS-61767] Isolate theme projects configuration (c5b3b7b10b)
- [LPS-61767] Isolate module projects configuration (c648ac7dd0)
- [LPS-61767] Avoid copying .touch files from the configs dir (1841c80068)
- [LPS-61767] Improve root task descriptions (891ae80f1a)
- [LPS-61767] Isolate root project configuration (b34f5684a3)
- [LPS-61767] Add "configs.dir" extension property (f7d725032e)
- [LPS-61767] Extract constant (3bbc66409b)
- [LPS-61767] Project configurator skeletons (3fee777f9d)
- [LPS-61767] Interface and base impl of the different project configurators
(0f07252634)

### Dependencies
- [LPS-61767] Update the com.liferay.gradle.plugins dependency to version
1.0.91.

## 1.0.4 - 2016-01-18

### Commits
- [LPS-62167] Add liferay-gradle-build-lang-plugin required parameters
(284d2354d2)

## 1.0.3 - 2016-01-14

### Commits
- [LPS-62088] added gradle wrapper (dc28731933)
- [LPS-61767] Source formatting (f70e158681)

## 1.0.2 - 2016-01-13

### Commits
- [LPS-61767] Automatically update the plugin version inside "samples"
(1fa5e3e151)
- [LPS-61767] Prevent downloading the bundle with "gradle tasks" (fc37f3e291)
- [LPS-61767] Include the "samples" dir inside the published sources jar
(bc63039681)
- [LPS-61767] Move workspace skeleton out of the jar (99deedaafa)
- [LPS-61767] copy configs on distBundle (a0c1d39392)
- [LPS-61767] remove mavenLocal (8c4ee8a25c)

### Dependencies
- [LPS-61767] Update the com.liferay.gradle.plugins dependency to version
1.0.78.

## 1.0.1 - 2016-01-12

### Commits
- [LPS-61767] Rename (5b6be2d01b)
- [LPS-61767] add liferay repo to modules' projects (3860e12054)

### Dependencies
- [LPS-61767] Update the com.liferay.gradle.plugins dependency to version
1.0.74.
- [LPS-61767] Update the com.liferay.gradle.plugins dependency to version
1.0.73.

[BLADE-255]: https://issues.liferay.com/browse/BLADE-255
[COMMERCE-11180]: https://issues.liferay.com/browse/COMMERCE-11180
[COMMERCE-12719]: https://issues.liferay.com/browse/COMMERCE-12719
[COMMERCE-12722]: https://issues.liferay.com/browse/COMMERCE-12722
[COMMERCE-12879]: https://issues.liferay.com/browse/COMMERCE-12879
[IDE-4081]: https://issues.liferay.com/browse/IDE-4081
[LCD-14295]: https://issues.liferay.com/browse/LCD-14295
[LCD-14464]: https://issues.liferay.com/browse/LCD-14464
[LCD-14591]: https://issues.liferay.com/browse/LCD-14591
[LCD-14678]: https://issues.liferay.com/browse/LCD-14678
[LCD-24326]: https://issues.liferay.com/browse/LCD-24326
[LPD-1783]: https://issues.liferay.com/browse/LPD-1783
[LPD-5443]: https://issues.liferay.com/browse/LPD-5443
[LPD-6610]: https://issues.liferay.com/browse/LPD-6610
[LPD-6870]: https://issues.liferay.com/browse/LPD-6870
[LPD-7009]: https://issues.liferay.com/browse/LPD-7009
[LPD-7996]: https://issues.liferay.com/browse/LPD-7996
[LPD-8052]: https://issues.liferay.com/browse/LPD-8052
[LPD-8724]: https://issues.liferay.com/browse/LPD-8724
[LPD-15162]: https://issues.liferay.com/browse/LPD-15162
[LPD-15285]: https://issues.liferay.com/browse/LPD-15285
[LPD-16079]: https://issues.liferay.com/browse/LPD-16079
[LPD-16384]: https://issues.liferay.com/browse/LPD-16384
[LPD-16391]: https://issues.liferay.com/browse/LPD-16391
[LPD-16636]: https://issues.liferay.com/browse/LPD-16636
[LPD-17470]: https://issues.liferay.com/browse/LPD-17470
[LPD-17973]: https://issues.liferay.com/browse/LPD-17973
[LPD-18192]: https://issues.liferay.com/browse/LPD-18192
[LPD-18565]: https://issues.liferay.com/browse/LPD-18565
[LPD-18681]: https://issues.liferay.com/browse/LPD-18681
[LPD-19220]: https://issues.liferay.com/browse/LPD-19220
[LPD-19346]: https://issues.liferay.com/browse/LPD-19346
[LPD-19659]: https://issues.liferay.com/browse/LPD-19659
[LPD-20533]: https://issues.liferay.com/browse/LPD-20533
[LPD-21814]: https://issues.liferay.com/browse/LPD-21814
[LPD-22159]: https://issues.liferay.com/browse/LPD-22159
[LPD-22405]: https://issues.liferay.com/browse/LPD-22405
[LPD-24198]: https://issues.liferay.com/browse/LPD-24198
[LPD-24745]: https://issues.liferay.com/browse/LPD-24745
[LPD-24834]: https://issues.liferay.com/browse/LPD-24834
[LPD-25246]: https://issues.liferay.com/browse/LPD-25246
[LPD-25819]: https://issues.liferay.com/browse/LPD-25819
[LPD-26157]: https://issues.liferay.com/browse/LPD-26157
[LPD-26438]: https://issues.liferay.com/browse/LPD-26438
[LPD-26455]: https://issues.liferay.com/browse/LPD-26455
[LPD-26470]: https://issues.liferay.com/browse/LPD-26470
[LPD-26792]: https://issues.liferay.com/browse/LPD-26792
[LPD-26999]: https://issues.liferay.com/browse/LPD-26999
[LPD-27018]: https://issues.liferay.com/browse/LPD-27018
[LPD-27355]: https://issues.liferay.com/browse/LPD-27355
[LPD-27502]: https://issues.liferay.com/browse/LPD-27502
[LPD-27535]: https://issues.liferay.com/browse/LPD-27535
[LPD-28218]: https://issues.liferay.com/browse/LPD-28218
[LPD-28604]: https://issues.liferay.com/browse/LPD-28604
[LPD-28762]: https://issues.liferay.com/browse/LPD-28762
[LPD-28815]: https://issues.liferay.com/browse/LPD-28815
[LPD-29047]: https://issues.liferay.com/browse/LPD-29047
[LPD-29733]: https://issues.liferay.com/browse/LPD-29733
[LPD-30270]: https://issues.liferay.com/browse/LPD-30270
[LPD-30302]: https://issues.liferay.com/browse/LPD-30302
[LPD-31478]: https://issues.liferay.com/browse/LPD-31478
[LPD-31541]: https://issues.liferay.com/browse/LPD-31541
[LPD-32731]: https://issues.liferay.com/browse/LPD-32731
[LPD-33240]: https://issues.liferay.com/browse/LPD-33240
[LPD-33244]: https://issues.liferay.com/browse/LPD-33244
[LPD-33272]: https://issues.liferay.com/browse/LPD-33272
[LPD-33601]: https://issues.liferay.com/browse/LPD-33601
[LPS-20287]: https://issues.liferay.com/browse/LPS-20287
[LPS-41848]: https://issues.liferay.com/browse/LPS-41848
[LPS-51081]: https://issues.liferay.com/browse/LPS-51081
[LPS-52675]: https://issues.liferay.com/browse/LPS-52675
[LPS-53392]: https://issues.liferay.com/browse/LPS-53392
[LPS-55111]: https://issues.liferay.com/browse/LPS-55111
[LPS-55250]: https://issues.liferay.com/browse/LPS-55250
[LPS-58672]: https://issues.liferay.com/browse/LPS-58672
[LPS-60972]: https://issues.liferay.com/browse/LPS-60972
[LPS-61088]: https://issues.liferay.com/browse/LPS-61088
[LPS-61099]: https://issues.liferay.com/browse/LPS-61099
[LPS-61420]: https://issues.liferay.com/browse/LPS-61420
[LPS-61443]: https://issues.liferay.com/browse/LPS-61443
[LPS-61720]: https://issues.liferay.com/browse/LPS-61720
[LPS-61767]: https://issues.liferay.com/browse/LPS-61767
[LPS-61952]: https://issues.liferay.com/browse/LPS-61952
[LPS-62088]: https://issues.liferay.com/browse/LPS-62088
[LPS-62167]: https://issues.liferay.com/browse/LPS-62167
[LPS-62450]: https://issues.liferay.com/browse/LPS-62450
[LPS-62485]: https://issues.liferay.com/browse/LPS-62485
[LPS-62493]: https://issues.liferay.com/browse/LPS-62493
[LPS-62504]: https://issues.liferay.com/browse/LPS-62504
[LPS-62570]: https://issues.liferay.com/browse/LPS-62570
[LPS-62741]: https://issues.liferay.com/browse/LPS-62741
[LPS-62883]: https://issues.liferay.com/browse/LPS-62883
[LPS-62942]: https://issues.liferay.com/browse/LPS-62942
[LPS-62970]: https://issues.liferay.com/browse/LPS-62970
[LPS-62986]: https://issues.liferay.com/browse/LPS-62986
[LPS-62989]: https://issues.liferay.com/browse/LPS-62989
[LPS-63112]: https://issues.liferay.com/browse/LPS-63112
[LPS-63161]: https://issues.liferay.com/browse/LPS-63161
[LPS-63162]: https://issues.liferay.com/browse/LPS-63162
[LPS-63182]: https://issues.liferay.com/browse/LPS-63182
[LPS-63309]: https://issues.liferay.com/browse/LPS-63309
[LPS-63410]: https://issues.liferay.com/browse/LPS-63410
[LPS-63462]: https://issues.liferay.com/browse/LPS-63462
[LPS-63470]: https://issues.liferay.com/browse/LPS-63470
[LPS-63493]: https://issues.liferay.com/browse/LPS-63493
[LPS-63499]: https://issues.liferay.com/browse/LPS-63499
[LPS-63525]: https://issues.liferay.com/browse/LPS-63525
[LPS-63558]: https://issues.liferay.com/browse/LPS-63558
[LPS-63631]: https://issues.liferay.com/browse/LPS-63631
[LPS-63706]: https://issues.liferay.com/browse/LPS-63706
[LPS-63734]: https://issues.liferay.com/browse/LPS-63734
[LPS-63740]: https://issues.liferay.com/browse/LPS-63740
[LPS-63791]: https://issues.liferay.com/browse/LPS-63791
[LPS-63792]: https://issues.liferay.com/browse/LPS-63792
[LPS-63797]: https://issues.liferay.com/browse/LPS-63797
[LPS-63855]: https://issues.liferay.com/browse/LPS-63855
[LPS-63857]: https://issues.liferay.com/browse/LPS-63857
[LPS-63864]: https://issues.liferay.com/browse/LPS-63864
[LPS-63943]: https://issues.liferay.com/browse/LPS-63943
[LPS-64002]: https://issues.liferay.com/browse/LPS-64002
[LPS-64021]: https://issues.liferay.com/browse/LPS-64021
[LPS-64029]: https://issues.liferay.com/browse/LPS-64029
[LPS-64031]: https://issues.liferay.com/browse/LPS-64031
[LPS-64076]: https://issues.liferay.com/browse/LPS-64076
[LPS-64092]: https://issues.liferay.com/browse/LPS-64092
[LPS-64098]: https://issues.liferay.com/browse/LPS-64098
[LPS-64182]: https://issues.liferay.com/browse/LPS-64182
[LPS-64186]: https://issues.liferay.com/browse/LPS-64186
[LPS-64188]: https://issues.liferay.com/browse/LPS-64188
[LPS-64191]: https://issues.liferay.com/browse/LPS-64191
[LPS-64281]: https://issues.liferay.com/browse/LPS-64281
[LPS-64309]: https://issues.liferay.com/browse/LPS-64309
[LPS-64343]: https://issues.liferay.com/browse/LPS-64343
[LPS-64376]: https://issues.liferay.com/browse/LPS-64376
[LPS-64383]: https://issues.liferay.com/browse/LPS-64383
[LPS-64407]: https://issues.liferay.com/browse/LPS-64407
[LPS-64532]: https://issues.liferay.com/browse/LPS-64532
[LPS-64586]: https://issues.liferay.com/browse/LPS-64586
[LPS-64596]: https://issues.liferay.com/browse/LPS-64596
[LPS-64619]: https://issues.liferay.com/browse/LPS-64619
[LPS-64654]: https://issues.liferay.com/browse/LPS-64654
[LPS-64668]: https://issues.liferay.com/browse/LPS-64668
[LPS-64713]: https://issues.liferay.com/browse/LPS-64713
[LPS-64719]: https://issues.liferay.com/browse/LPS-64719
[LPS-64755]: https://issues.liferay.com/browse/LPS-64755
[LPS-64761]: https://issues.liferay.com/browse/LPS-64761
[LPS-64786]: https://issues.liferay.com/browse/LPS-64786
[LPS-64816]: https://issues.liferay.com/browse/LPS-64816
[LPS-64847]: https://issues.liferay.com/browse/LPS-64847
[LPS-64875]: https://issues.liferay.com/browse/LPS-64875
[LPS-64897]: https://issues.liferay.com/browse/LPS-64897
[LPS-64991]: https://issues.liferay.com/browse/LPS-64991
[LPS-65000]: https://issues.liferay.com/browse/LPS-65000
[LPS-65064]: https://issues.liferay.com/browse/LPS-65064
[LPS-65072]: https://issues.liferay.com/browse/LPS-65072
[LPS-65086]: https://issues.liferay.com/browse/LPS-65086
[LPS-65094]: https://issues.liferay.com/browse/LPS-65094
[LPS-65119]: https://issues.liferay.com/browse/LPS-65119
[LPS-65135]: https://issues.liferay.com/browse/LPS-65135
[LPS-65179]: https://issues.liferay.com/browse/LPS-65179
[LPS-65220]: https://issues.liferay.com/browse/LPS-65220
[LPS-65232]: https://issues.liferay.com/browse/LPS-65232
[LPS-65245]: https://issues.liferay.com/browse/LPS-65245
[LPS-65283]: https://issues.liferay.com/browse/LPS-65283
[LPS-65316]: https://issues.liferay.com/browse/LPS-65316
[LPS-65323]: https://issues.liferay.com/browse/LPS-65323
[LPS-65338]: https://issues.liferay.com/browse/LPS-65338
[LPS-65362]: https://issues.liferay.com/browse/LPS-65362
[LPS-65383]: https://issues.liferay.com/browse/LPS-65383
[LPS-65387]: https://issues.liferay.com/browse/LPS-65387
[LPS-65490]: https://issues.liferay.com/browse/LPS-65490
[LPS-65528]: https://issues.liferay.com/browse/LPS-65528
[LPS-65568]: https://issues.liferay.com/browse/LPS-65568
[LPS-65633]: https://issues.liferay.com/browse/LPS-65633
[LPS-65636]: https://issues.liferay.com/browse/LPS-65636
[LPS-65685]: https://issues.liferay.com/browse/LPS-65685
[LPS-65693]: https://issues.liferay.com/browse/LPS-65693
[LPS-65716]: https://issues.liferay.com/browse/LPS-65716
[LPS-65741]: https://issues.liferay.com/browse/LPS-65741
[LPS-65749]: https://issues.liferay.com/browse/LPS-65749
[LPS-65753]: https://issues.liferay.com/browse/LPS-65753
[LPS-65786]: https://issues.liferay.com/browse/LPS-65786
[LPS-65788]: https://issues.liferay.com/browse/LPS-65788
[LPS-65799]: https://issues.liferay.com/browse/LPS-65799
[LPS-65810]: https://issues.liferay.com/browse/LPS-65810
[LPS-65845]: https://issues.liferay.com/browse/LPS-65845
[LPS-65920]: https://issues.liferay.com/browse/LPS-65920
[LPS-65930]: https://issues.liferay.com/browse/LPS-65930
[LPS-65971]: https://issues.liferay.com/browse/LPS-65971
[LPS-65976]: https://issues.liferay.com/browse/LPS-65976
[LPS-66010]: https://issues.liferay.com/browse/LPS-66010
[LPS-66050]: https://issues.liferay.com/browse/LPS-66050
[LPS-66061]: https://issues.liferay.com/browse/LPS-66061
[LPS-66064]: https://issues.liferay.com/browse/LPS-66064
[LPS-66099]: https://issues.liferay.com/browse/LPS-66099
[LPS-66139]: https://issues.liferay.com/browse/LPS-66139
[LPS-66222]: https://issues.liferay.com/browse/LPS-66222
[LPS-66242]: https://issues.liferay.com/browse/LPS-66242
[LPS-66281]: https://issues.liferay.com/browse/LPS-66281
[LPS-66282]: https://issues.liferay.com/browse/LPS-66282
[LPS-66324]: https://issues.liferay.com/browse/LPS-66324
[LPS-66410]: https://issues.liferay.com/browse/LPS-66410
[LPS-66413]: https://issues.liferay.com/browse/LPS-66413
[LPS-66431]: https://issues.liferay.com/browse/LPS-66431
[LPS-66473]: https://issues.liferay.com/browse/LPS-66473
[LPS-66595]: https://issues.liferay.com/browse/LPS-66595
[LPS-66639]: https://issues.liferay.com/browse/LPS-66639
[LPS-66663]: https://issues.liferay.com/browse/LPS-66663
[LPS-66698]: https://issues.liferay.com/browse/LPS-66698
[LPS-66709]: https://issues.liferay.com/browse/LPS-66709
[LPS-66731]: https://issues.liferay.com/browse/LPS-66731
[LPS-66762]: https://issues.liferay.com/browse/LPS-66762
[LPS-66797]: https://issues.liferay.com/browse/LPS-66797
[LPS-66853]: https://issues.liferay.com/browse/LPS-66853
[LPS-66860]: https://issues.liferay.com/browse/LPS-66860
[LPS-66883]: https://issues.liferay.com/browse/LPS-66883
[LPS-66891]: https://issues.liferay.com/browse/LPS-66891
[LPS-66906]: https://issues.liferay.com/browse/LPS-66906
[LPS-66941]: https://issues.liferay.com/browse/LPS-66941
[LPS-66951]: https://issues.liferay.com/browse/LPS-66951
[LPS-67023]: https://issues.liferay.com/browse/LPS-67023
[LPS-67029]: https://issues.liferay.com/browse/LPS-67029
[LPS-67042]: https://issues.liferay.com/browse/LPS-67042
[LPS-67048]: https://issues.liferay.com/browse/LPS-67048
[LPS-67111]: https://issues.liferay.com/browse/LPS-67111
[LPS-67151]: https://issues.liferay.com/browse/LPS-67151
[LPS-67190]: https://issues.liferay.com/browse/LPS-67190
[LPS-67314]: https://issues.liferay.com/browse/LPS-67314
[LPS-67352]: https://issues.liferay.com/browse/LPS-67352
[LPS-67434]: https://issues.liferay.com/browse/LPS-67434
[LPS-67483]: https://issues.liferay.com/browse/LPS-67483
[LPS-67495]: https://issues.liferay.com/browse/LPS-67495
[LPS-67503]: https://issues.liferay.com/browse/LPS-67503
[LPS-67544]: https://issues.liferay.com/browse/LPS-67544
[LPS-67565]: https://issues.liferay.com/browse/LPS-67565
[LPS-67573]: https://issues.liferay.com/browse/LPS-67573
[LPS-67596]: https://issues.liferay.com/browse/LPS-67596
[LPS-67653]: https://issues.liferay.com/browse/LPS-67653
[LPS-67656]: https://issues.liferay.com/browse/LPS-67656
[LPS-67658]: https://issues.liferay.com/browse/LPS-67658
[LPS-67688]: https://issues.liferay.com/browse/LPS-67688
[LPS-67694]: https://issues.liferay.com/browse/LPS-67694
[LPS-67766]: https://issues.liferay.com/browse/LPS-67766
[LPS-67804]: https://issues.liferay.com/browse/LPS-67804
[LPS-67952]: https://issues.liferay.com/browse/LPS-67952
[LPS-67986]: https://issues.liferay.com/browse/LPS-67986
[LPS-67996]: https://issues.liferay.com/browse/LPS-67996
[LPS-68035]: https://issues.liferay.com/browse/LPS-68035
[LPS-68131]: https://issues.liferay.com/browse/LPS-68131
[LPS-68289]: https://issues.liferay.com/browse/LPS-68289
[LPS-68293]: https://issues.liferay.com/browse/LPS-68293
[LPS-68297]: https://issues.liferay.com/browse/LPS-68297
[LPS-68298]: https://issues.liferay.com/browse/LPS-68298
[LPS-68334]: https://issues.liferay.com/browse/LPS-68334
[LPS-68361]: https://issues.liferay.com/browse/LPS-68361
[LPS-68405]: https://issues.liferay.com/browse/LPS-68405
[LPS-68415]: https://issues.liferay.com/browse/LPS-68415
[LPS-68485]: https://issues.liferay.com/browse/LPS-68485
[LPS-68506]: https://issues.liferay.com/browse/LPS-68506
[LPS-68564]: https://issues.liferay.com/browse/LPS-68564
[LPS-68598]: https://issues.liferay.com/browse/LPS-68598
[LPS-68618]: https://issues.liferay.com/browse/LPS-68618
[LPS-68666]: https://issues.liferay.com/browse/LPS-68666
[LPS-68779]: https://issues.liferay.com/browse/LPS-68779
[LPS-68817]: https://issues.liferay.com/browse/LPS-68817
[LPS-68822]: https://issues.liferay.com/browse/LPS-68822
[LPS-68838]: https://issues.liferay.com/browse/LPS-68838
[LPS-68839]: https://issues.liferay.com/browse/LPS-68839
[LPS-68849]: https://issues.liferay.com/browse/LPS-68849
[LPS-68917]: https://issues.liferay.com/browse/LPS-68917
[LPS-68923]: https://issues.liferay.com/browse/LPS-68923
[LPS-68980]: https://issues.liferay.com/browse/LPS-68980
[LPS-69013]: https://issues.liferay.com/browse/LPS-69013
[LPS-69026]: https://issues.liferay.com/browse/LPS-69026
[LPS-69035]: https://issues.liferay.com/browse/LPS-69035
[LPS-69139]: https://issues.liferay.com/browse/LPS-69139
[LPS-69248]: https://issues.liferay.com/browse/LPS-69248
[LPS-69259]: https://issues.liferay.com/browse/LPS-69259
[LPS-69271]: https://issues.liferay.com/browse/LPS-69271
[LPS-69294]: https://issues.liferay.com/browse/LPS-69294
[LPS-69445]: https://issues.liferay.com/browse/LPS-69445
[LPS-69453]: https://issues.liferay.com/browse/LPS-69453
[LPS-69473]: https://issues.liferay.com/browse/LPS-69473
[LPS-69488]: https://issues.liferay.com/browse/LPS-69488
[LPS-69492]: https://issues.liferay.com/browse/LPS-69492
[LPS-69501]: https://issues.liferay.com/browse/LPS-69501
[LPS-69606]: https://issues.liferay.com/browse/LPS-69606
[LPS-69677]: https://issues.liferay.com/browse/LPS-69677
[LPS-69706]: https://issues.liferay.com/browse/LPS-69706
[LPS-69730]: https://issues.liferay.com/browse/LPS-69730
[LPS-69802]: https://issues.liferay.com/browse/LPS-69802
[LPS-69838]: https://issues.liferay.com/browse/LPS-69838
[LPS-69920]: https://issues.liferay.com/browse/LPS-69920
[LPS-69926]: https://issues.liferay.com/browse/LPS-69926
[LPS-70036]: https://issues.liferay.com/browse/LPS-70036
[LPS-70060]: https://issues.liferay.com/browse/LPS-70060
[LPS-70092]: https://issues.liferay.com/browse/LPS-70092
[LPS-70170]: https://issues.liferay.com/browse/LPS-70170
[LPS-70282]: https://issues.liferay.com/browse/LPS-70282
[LPS-70286]: https://issues.liferay.com/browse/LPS-70286
[LPS-70335]: https://issues.liferay.com/browse/LPS-70335
[LPS-70353]: https://issues.liferay.com/browse/LPS-70353
[LPS-70362]: https://issues.liferay.com/browse/LPS-70362
[LPS-70424]: https://issues.liferay.com/browse/LPS-70424
[LPS-70486]: https://issues.liferay.com/browse/LPS-70486
[LPS-70494]: https://issues.liferay.com/browse/LPS-70494
[LPS-70584]: https://issues.liferay.com/browse/LPS-70584
[LPS-70604]: https://issues.liferay.com/browse/LPS-70604
[LPS-70634]: https://issues.liferay.com/browse/LPS-70634
[LPS-70677]: https://issues.liferay.com/browse/LPS-70677
[LPS-70707]: https://issues.liferay.com/browse/LPS-70707
[LPS-70819]: https://issues.liferay.com/browse/LPS-70819
[LPS-70890]: https://issues.liferay.com/browse/LPS-70890
[LPS-70929]: https://issues.liferay.com/browse/LPS-70929
[LPS-71005]: https://issues.liferay.com/browse/LPS-71005
[LPS-71117]: https://issues.liferay.com/browse/LPS-71117
[LPS-71164]: https://issues.liferay.com/browse/LPS-71164
[LPS-71201]: https://issues.liferay.com/browse/LPS-71201
[LPS-71222]: https://issues.liferay.com/browse/LPS-71222
[LPS-71303]: https://issues.liferay.com/browse/LPS-71303
[LPS-71376]: https://issues.liferay.com/browse/LPS-71376
[LPS-71535]: https://issues.liferay.com/browse/LPS-71535
[LPS-71591]: https://issues.liferay.com/browse/LPS-71591
[LPS-71686]: https://issues.liferay.com/browse/LPS-71686
[LPS-71722]: https://issues.liferay.com/browse/LPS-71722
[LPS-71724]: https://issues.liferay.com/browse/LPS-71724
[LPS-71728]: https://issues.liferay.com/browse/LPS-71728
[LPS-71826]: https://issues.liferay.com/browse/LPS-71826
[LPS-71901]: https://issues.liferay.com/browse/LPS-71901
[LPS-71925]: https://issues.liferay.com/browse/LPS-71925
[LPS-72030]: https://issues.liferay.com/browse/LPS-72030
[LPS-72039]: https://issues.liferay.com/browse/LPS-72039
[LPS-72067]: https://issues.liferay.com/browse/LPS-72067
[LPS-72252]: https://issues.liferay.com/browse/LPS-72252
[LPS-72340]: https://issues.liferay.com/browse/LPS-72340
[LPS-72347]: https://issues.liferay.com/browse/LPS-72347
[LPS-72365]: https://issues.liferay.com/browse/LPS-72365
[LPS-72465]: https://issues.liferay.com/browse/LPS-72465
[LPS-72562]: https://issues.liferay.com/browse/LPS-72562
[LPS-72572]: https://issues.liferay.com/browse/LPS-72572
[LPS-72705]: https://issues.liferay.com/browse/LPS-72705
[LPS-72750]: https://issues.liferay.com/browse/LPS-72750
[LPS-72830]: https://issues.liferay.com/browse/LPS-72830
[LPS-72851]: https://issues.liferay.com/browse/LPS-72851
[LPS-72875]: https://issues.liferay.com/browse/LPS-72875
[LPS-72912]: https://issues.liferay.com/browse/LPS-72912
[LPS-72914]: https://issues.liferay.com/browse/LPS-72914
[LPS-73056]: https://issues.liferay.com/browse/LPS-73056
[LPS-73070]: https://issues.liferay.com/browse/LPS-73070
[LPS-73124]: https://issues.liferay.com/browse/LPS-73124
[LPS-73248]: https://issues.liferay.com/browse/LPS-73248
[LPS-73261]: https://issues.liferay.com/browse/LPS-73261
[LPS-73273]: https://issues.liferay.com/browse/LPS-73273
[LPS-73289]: https://issues.liferay.com/browse/LPS-73289
[LPS-73353]: https://issues.liferay.com/browse/LPS-73353
[LPS-73383]: https://issues.liferay.com/browse/LPS-73383
[LPS-73472]: https://issues.liferay.com/browse/LPS-73472
[LPS-73481]: https://issues.liferay.com/browse/LPS-73481
[LPS-73495]: https://issues.liferay.com/browse/LPS-73495
[LPS-73506]: https://issues.liferay.com/browse/LPS-73506
[LPS-73525]: https://issues.liferay.com/browse/LPS-73525
[LPS-73584]: https://issues.liferay.com/browse/LPS-73584
[LPS-73600]: https://issues.liferay.com/browse/LPS-73600
[LPS-73607]: https://issues.liferay.com/browse/LPS-73607
[LPS-73655]: https://issues.liferay.com/browse/LPS-73655
[LPS-73725]: https://issues.liferay.com/browse/LPS-73725
[LPS-73746]: https://issues.liferay.com/browse/LPS-73746
[LPS-73818]: https://issues.liferay.com/browse/LPS-73818
[LPS-73855]: https://issues.liferay.com/browse/LPS-73855
[LPS-73913]: https://issues.liferay.com/browse/LPS-73913
[LPS-73935]: https://issues.liferay.com/browse/LPS-73935
[LPS-73967]: https://issues.liferay.com/browse/LPS-73967
[LPS-74034]: https://issues.liferay.com/browse/LPS-74034
[LPS-74063]: https://issues.liferay.com/browse/LPS-74063
[LPS-74088]: https://issues.liferay.com/browse/LPS-74088
[LPS-74092]: https://issues.liferay.com/browse/LPS-74092
[LPS-74102]: https://issues.liferay.com/browse/LPS-74102
[LPS-74104]: https://issues.liferay.com/browse/LPS-74104
[LPS-74110]: https://issues.liferay.com/browse/LPS-74110
[LPS-74124]: https://issues.liferay.com/browse/LPS-74124
[LPS-74126]: https://issues.liferay.com/browse/LPS-74126
[LPS-74139]: https://issues.liferay.com/browse/LPS-74139
[LPS-74143]: https://issues.liferay.com/browse/LPS-74143
[LPS-74155]: https://issues.liferay.com/browse/LPS-74155
[LPS-74171]: https://issues.liferay.com/browse/LPS-74171
[LPS-74207]: https://issues.liferay.com/browse/LPS-74207
[LPS-74222]: https://issues.liferay.com/browse/LPS-74222
[LPS-74265]: https://issues.liferay.com/browse/LPS-74265
[LPS-74278]: https://issues.liferay.com/browse/LPS-74278
[LPS-74314]: https://issues.liferay.com/browse/LPS-74314
[LPS-74315]: https://issues.liferay.com/browse/LPS-74315
[LPS-74343]: https://issues.liferay.com/browse/LPS-74343
[LPS-74345]: https://issues.liferay.com/browse/LPS-74345
[LPS-74348]: https://issues.liferay.com/browse/LPS-74348
[LPS-74368]: https://issues.liferay.com/browse/LPS-74368
[LPS-74369]: https://issues.liferay.com/browse/LPS-74369
[LPS-74373]: https://issues.liferay.com/browse/LPS-74373
[LPS-74426]: https://issues.liferay.com/browse/LPS-74426
[LPS-74449]: https://issues.liferay.com/browse/LPS-74449
[LPS-74457]: https://issues.liferay.com/browse/LPS-74457
[LPS-74475]: https://issues.liferay.com/browse/LPS-74475
[LPS-74490]: https://issues.liferay.com/browse/LPS-74490
[LPS-74526]: https://issues.liferay.com/browse/LPS-74526
[LPS-74538]: https://issues.liferay.com/browse/LPS-74538
[LPS-74544]: https://issues.liferay.com/browse/LPS-74544
[LPS-74608]: https://issues.liferay.com/browse/LPS-74608
[LPS-74614]: https://issues.liferay.com/browse/LPS-74614
[LPS-74637]: https://issues.liferay.com/browse/LPS-74637
[LPS-74657]: https://issues.liferay.com/browse/LPS-74657
[LPS-74738]: https://issues.liferay.com/browse/LPS-74738
[LPS-74749]: https://issues.liferay.com/browse/LPS-74749
[LPS-74770]: https://issues.liferay.com/browse/LPS-74770
[LPS-74789]: https://issues.liferay.com/browse/LPS-74789
[LPS-74818]: https://issues.liferay.com/browse/LPS-74818
[LPS-74849]: https://issues.liferay.com/browse/LPS-74849
[LPS-74867]: https://issues.liferay.com/browse/LPS-74867
[LPS-74872]: https://issues.liferay.com/browse/LPS-74872
[LPS-74884]: https://issues.liferay.com/browse/LPS-74884
[LPS-74904]: https://issues.liferay.com/browse/LPS-74904
[LPS-74933]: https://issues.liferay.com/browse/LPS-74933
[LPS-75009]: https://issues.liferay.com/browse/LPS-75009
[LPS-75010]: https://issues.liferay.com/browse/LPS-75010
[LPS-75039]: https://issues.liferay.com/browse/LPS-75039
[LPS-75049]: https://issues.liferay.com/browse/LPS-75049
[LPS-75096]: https://issues.liferay.com/browse/LPS-75096
[LPS-75100]: https://issues.liferay.com/browse/LPS-75100
[LPS-75175]: https://issues.liferay.com/browse/LPS-75175
[LPS-75238]: https://issues.liferay.com/browse/LPS-75238
[LPS-75239]: https://issues.liferay.com/browse/LPS-75239
[LPS-75254]: https://issues.liferay.com/browse/LPS-75254
[LPS-75273]: https://issues.liferay.com/browse/LPS-75273
[LPS-75323]: https://issues.liferay.com/browse/LPS-75323
[LPS-75399]: https://issues.liferay.com/browse/LPS-75399
[LPS-75427]: https://issues.liferay.com/browse/LPS-75427
[LPS-75479]: https://issues.liferay.com/browse/LPS-75479
[LPS-75488]: https://issues.liferay.com/browse/LPS-75488
[LPS-75530]: https://issues.liferay.com/browse/LPS-75530
[LPS-75589]: https://issues.liferay.com/browse/LPS-75589
[LPS-75610]: https://issues.liferay.com/browse/LPS-75610
[LPS-75613]: https://issues.liferay.com/browse/LPS-75613
[LPS-75633]: https://issues.liferay.com/browse/LPS-75633
[LPS-75705]: https://issues.liferay.com/browse/LPS-75705
[LPS-75798]: https://issues.liferay.com/browse/LPS-75798
[LPS-75859]: https://issues.liferay.com/browse/LPS-75859
[LPS-75901]: https://issues.liferay.com/browse/LPS-75901
[LPS-75965]: https://issues.liferay.com/browse/LPS-75965
[LPS-76018]: https://issues.liferay.com/browse/LPS-76018
[LPS-76110]: https://issues.liferay.com/browse/LPS-76110
[LPS-76145]: https://issues.liferay.com/browse/LPS-76145
[LPS-76202]: https://issues.liferay.com/browse/LPS-76202
[LPS-76221]: https://issues.liferay.com/browse/LPS-76221
[LPS-76224]: https://issues.liferay.com/browse/LPS-76224
[LPS-76226]: https://issues.liferay.com/browse/LPS-76226
[LPS-76256]: https://issues.liferay.com/browse/LPS-76256
[LPS-76271]: https://issues.liferay.com/browse/LPS-76271
[LPS-76326]: https://issues.liferay.com/browse/LPS-76326
[LPS-76475]: https://issues.liferay.com/browse/LPS-76475
[LPS-76626]: https://issues.liferay.com/browse/LPS-76626
[LPS-76644]: https://issues.liferay.com/browse/LPS-76644
[LPS-76840]: https://issues.liferay.com/browse/LPS-76840
[LPS-76926]: https://issues.liferay.com/browse/LPS-76926
[LPS-76997]: https://issues.liferay.com/browse/LPS-76997
[LPS-77110]: https://issues.liferay.com/browse/LPS-77110
[LPS-77143]: https://issues.liferay.com/browse/LPS-77143
[LPS-77286]: https://issues.liferay.com/browse/LPS-77286
[LPS-77350]: https://issues.liferay.com/browse/LPS-77350
[LPS-77352]: https://issues.liferay.com/browse/LPS-77352
[LPS-77359]: https://issues.liferay.com/browse/LPS-77359
[LPS-77400]: https://issues.liferay.com/browse/LPS-77400
[LPS-77402]: https://issues.liferay.com/browse/LPS-77402
[LPS-77423]: https://issues.liferay.com/browse/LPS-77423
[LPS-77425]: https://issues.liferay.com/browse/LPS-77425
[LPS-77441]: https://issues.liferay.com/browse/LPS-77441
[LPS-77459]: https://issues.liferay.com/browse/LPS-77459
[LPS-77532]: https://issues.liferay.com/browse/LPS-77532
[LPS-77577]: https://issues.liferay.com/browse/LPS-77577
[LPS-77586]: https://issues.liferay.com/browse/LPS-77586
[LPS-77630]: https://issues.liferay.com/browse/LPS-77630
[LPS-77639]: https://issues.liferay.com/browse/LPS-77639
[LPS-77645]: https://issues.liferay.com/browse/LPS-77645
[LPS-77699]: https://issues.liferay.com/browse/LPS-77699
[LPS-77795]: https://issues.liferay.com/browse/LPS-77795
[LPS-77836]: https://issues.liferay.com/browse/LPS-77836
[LPS-77840]: https://issues.liferay.com/browse/LPS-77840
[LPS-77875]: https://issues.liferay.com/browse/LPS-77875
[LPS-77886]: https://issues.liferay.com/browse/LPS-77886
[LPS-77897]: https://issues.liferay.com/browse/LPS-77897
[LPS-77916]: https://issues.liferay.com/browse/LPS-77916
[LPS-77996]: https://issues.liferay.com/browse/LPS-77996
[LPS-78033]: https://issues.liferay.com/browse/LPS-78033
[LPS-78038]: https://issues.liferay.com/browse/LPS-78038
[LPS-78071]: https://issues.liferay.com/browse/LPS-78071
[LPS-78096]: https://issues.liferay.com/browse/LPS-78096
[LPS-78149]: https://issues.liferay.com/browse/LPS-78149
[LPS-78150]: https://issues.liferay.com/browse/LPS-78150
[LPS-78266]: https://issues.liferay.com/browse/LPS-78266
[LPS-78269]: https://issues.liferay.com/browse/LPS-78269
[LPS-78288]: https://issues.liferay.com/browse/LPS-78288
[LPS-78308]: https://issues.liferay.com/browse/LPS-78308
[LPS-78312]: https://issues.liferay.com/browse/LPS-78312
[LPS-78457]: https://issues.liferay.com/browse/LPS-78457
[LPS-78459]: https://issues.liferay.com/browse/LPS-78459
[LPS-78477]: https://issues.liferay.com/browse/LPS-78477
[LPS-78493]: https://issues.liferay.com/browse/LPS-78493
[LPS-78537]: https://issues.liferay.com/browse/LPS-78537
[LPS-78571]: https://issues.liferay.com/browse/LPS-78571
[LPS-78741]: https://issues.liferay.com/browse/LPS-78741
[LPS-78750]: https://issues.liferay.com/browse/LPS-78750
[LPS-78767]: https://issues.liferay.com/browse/LPS-78767
[LPS-78772]: https://issues.liferay.com/browse/LPS-78772
[LPS-78845]: https://issues.liferay.com/browse/LPS-78845
[LPS-78901]: https://issues.liferay.com/browse/LPS-78901
[LPS-78911]: https://issues.liferay.com/browse/LPS-78911
[LPS-78938]: https://issues.liferay.com/browse/LPS-78938
[LPS-78940]: https://issues.liferay.com/browse/LPS-78940
[LPS-78971]: https://issues.liferay.com/browse/LPS-78971
[LPS-79131]: https://issues.liferay.com/browse/LPS-79131
[LPS-79191]: https://issues.liferay.com/browse/LPS-79191
[LPS-79192]: https://issues.liferay.com/browse/LPS-79192
[LPS-79226]: https://issues.liferay.com/browse/LPS-79226
[LPS-79239]: https://issues.liferay.com/browse/LPS-79239
[LPS-79240]: https://issues.liferay.com/browse/LPS-79240
[LPS-79248]: https://issues.liferay.com/browse/LPS-79248
[LPS-79262]: https://issues.liferay.com/browse/LPS-79262
[LPS-79286]: https://issues.liferay.com/browse/LPS-79286
[LPS-79360]: https://issues.liferay.com/browse/LPS-79360
[LPS-79365]: https://issues.liferay.com/browse/LPS-79365
[LPS-79386]: https://issues.liferay.com/browse/LPS-79386
[LPS-79388]: https://issues.liferay.com/browse/LPS-79388
[LPS-79450]: https://issues.liferay.com/browse/LPS-79450
[LPS-79453]: https://issues.liferay.com/browse/LPS-79453
[LPS-79576]: https://issues.liferay.com/browse/LPS-79576
[LPS-79623]: https://issues.liferay.com/browse/LPS-79623
[LPS-79653]: https://issues.liferay.com/browse/LPS-79653
[LPS-79679]: https://issues.liferay.com/browse/LPS-79679
[LPS-79709]: https://issues.liferay.com/browse/LPS-79709
[LPS-79755]: https://issues.liferay.com/browse/LPS-79755
[LPS-79799]: https://issues.liferay.com/browse/LPS-79799
[LPS-79919]: https://issues.liferay.com/browse/LPS-79919
[LPS-79963]: https://issues.liferay.com/browse/LPS-79963
[LPS-80054]: https://issues.liferay.com/browse/LPS-80054
[LPS-80064]: https://issues.liferay.com/browse/LPS-80064
[LPS-80122]: https://issues.liferay.com/browse/LPS-80122
[LPS-80123]: https://issues.liferay.com/browse/LPS-80123
[LPS-80125]: https://issues.liferay.com/browse/LPS-80125
[LPS-80184]: https://issues.liferay.com/browse/LPS-80184
[LPS-80222]: https://issues.liferay.com/browse/LPS-80222
[LPS-80332]: https://issues.liferay.com/browse/LPS-80332
[LPS-80386]: https://issues.liferay.com/browse/LPS-80386
[LPS-80388]: https://issues.liferay.com/browse/LPS-80388
[LPS-80394]: https://issues.liferay.com/browse/LPS-80394
[LPS-80466]: https://issues.liferay.com/browse/LPS-80466
[LPS-80513]: https://issues.liferay.com/browse/LPS-80513
[LPS-80517]: https://issues.liferay.com/browse/LPS-80517
[LPS-80544]: https://issues.liferay.com/browse/LPS-80544
[LPS-80660]: https://issues.liferay.com/browse/LPS-80660
[LPS-80723]: https://issues.liferay.com/browse/LPS-80723
[LPS-80777]: https://issues.liferay.com/browse/LPS-80777
[LPS-80840]: https://issues.liferay.com/browse/LPS-80840
[LPS-80920]: https://issues.liferay.com/browse/LPS-80920
[LPS-80927]: https://issues.liferay.com/browse/LPS-80927
[LPS-80944]: https://issues.liferay.com/browse/LPS-80944
[LPS-80950]: https://issues.liferay.com/browse/LPS-80950
[LPS-81106]: https://issues.liferay.com/browse/LPS-81106
[LPS-81336]: https://issues.liferay.com/browse/LPS-81336
[LPS-81404]: https://issues.liferay.com/browse/LPS-81404
[LPS-81555]: https://issues.liferay.com/browse/LPS-81555
[LPS-81635]: https://issues.liferay.com/browse/LPS-81635
[LPS-81638]: https://issues.liferay.com/browse/LPS-81638
[LPS-81704]: https://issues.liferay.com/browse/LPS-81704
[LPS-81706]: https://issues.liferay.com/browse/LPS-81706
[LPS-81795]: https://issues.liferay.com/browse/LPS-81795
[LPS-81895]: https://issues.liferay.com/browse/LPS-81895
[LPS-81944]: https://issues.liferay.com/browse/LPS-81944
[LPS-82001]: https://issues.liferay.com/browse/LPS-82001
[LPS-82091]: https://issues.liferay.com/browse/LPS-82091
[LPS-82121]: https://issues.liferay.com/browse/LPS-82121
[LPS-82178]: https://issues.liferay.com/browse/LPS-82178
[LPS-82261]: https://issues.liferay.com/browse/LPS-82261
[LPS-82343]: https://issues.liferay.com/browse/LPS-82343
[LPS-82420]: https://issues.liferay.com/browse/LPS-82420
[LPS-82433]: https://issues.liferay.com/browse/LPS-82433
[LPS-82491]: https://issues.liferay.com/browse/LPS-82491
[LPS-82534]: https://issues.liferay.com/browse/LPS-82534
[LPS-82568]: https://issues.liferay.com/browse/LPS-82568
[LPS-82828]: https://issues.liferay.com/browse/LPS-82828
[LPS-82857]: https://issues.liferay.com/browse/LPS-82857
[LPS-82960]: https://issues.liferay.com/browse/LPS-82960
[LPS-82976]: https://issues.liferay.com/browse/LPS-82976
[LPS-83067]: https://issues.liferay.com/browse/LPS-83067
[LPS-83104]: https://issues.liferay.com/browse/LPS-83104
[LPS-83168]: https://issues.liferay.com/browse/LPS-83168
[LPS-83220]: https://issues.liferay.com/browse/LPS-83220
[LPS-83483]: https://issues.liferay.com/browse/LPS-83483
[LPS-83520]: https://issues.liferay.com/browse/LPS-83520
[LPS-83576]: https://issues.liferay.com/browse/LPS-83576
[LPS-83705]: https://issues.liferay.com/browse/LPS-83705
[LPS-83755]: https://issues.liferay.com/browse/LPS-83755
[LPS-83761]: https://issues.liferay.com/browse/LPS-83761
[LPS-83790]: https://issues.liferay.com/browse/LPS-83790
[LPS-83922]: https://issues.liferay.com/browse/LPS-83922
[LPS-84039]: https://issues.liferay.com/browse/LPS-84039
[LPS-84055]: https://issues.liferay.com/browse/LPS-84055
[LPS-84094]: https://issues.liferay.com/browse/LPS-84094
[LPS-84119]: https://issues.liferay.com/browse/LPS-84119
[LPS-84138]: https://issues.liferay.com/browse/LPS-84138
[LPS-84213]: https://issues.liferay.com/browse/LPS-84213
[LPS-84307]: https://issues.liferay.com/browse/LPS-84307
[LPS-84473]: https://issues.liferay.com/browse/LPS-84473
[LPS-84621]: https://issues.liferay.com/browse/LPS-84621
[LPS-84624]: https://issues.liferay.com/browse/LPS-84624
[LPS-84887]: https://issues.liferay.com/browse/LPS-84887
[LPS-85035]: https://issues.liferay.com/browse/LPS-85035
[LPS-85092]: https://issues.liferay.com/browse/LPS-85092
[LPS-85296]: https://issues.liferay.com/browse/LPS-85296
[LPS-85556]: https://issues.liferay.com/browse/LPS-85556
[LPS-85609]: https://issues.liferay.com/browse/LPS-85609
[LPS-85678]: https://issues.liferay.com/browse/LPS-85678
[LPS-85828]: https://issues.liferay.com/browse/LPS-85828
[LPS-85849]: https://issues.liferay.com/browse/LPS-85849
[LPS-85855]: https://issues.liferay.com/browse/LPS-85855
[LPS-85946]: https://issues.liferay.com/browse/LPS-85946
[LPS-85954]: https://issues.liferay.com/browse/LPS-85954
[LPS-85959]: https://issues.liferay.com/browse/LPS-85959
[LPS-85987]: https://issues.liferay.com/browse/LPS-85987
[LPS-86018]: https://issues.liferay.com/browse/LPS-86018
[LPS-86308]: https://issues.liferay.com/browse/LPS-86308
[LPS-86324]: https://issues.liferay.com/browse/LPS-86324
[LPS-86362]: https://issues.liferay.com/browse/LPS-86362
[LPS-86371]: https://issues.liferay.com/browse/LPS-86371
[LPS-86406]: https://issues.liferay.com/browse/LPS-86406
[LPS-86413]: https://issues.liferay.com/browse/LPS-86413
[LPS-86447]: https://issues.liferay.com/browse/LPS-86447
[LPS-86493]: https://issues.liferay.com/browse/LPS-86493
[LPS-86528]: https://issues.liferay.com/browse/LPS-86528
[LPS-86549]: https://issues.liferay.com/browse/LPS-86549
[LPS-86576]: https://issues.liferay.com/browse/LPS-86576
[LPS-86581]: https://issues.liferay.com/browse/LPS-86581
[LPS-86583]: https://issues.liferay.com/browse/LPS-86583
[LPS-86705]: https://issues.liferay.com/browse/LPS-86705
[LPS-86806]: https://issues.liferay.com/browse/LPS-86806
[LPS-86853]: https://issues.liferay.com/browse/LPS-86853
[LPS-86916]: https://issues.liferay.com/browse/LPS-86916
[LPS-87192]: https://issues.liferay.com/browse/LPS-87192
[LPS-87293]: https://issues.liferay.com/browse/LPS-87293
[LPS-87366]: https://issues.liferay.com/browse/LPS-87366
[LPS-87371]: https://issues.liferay.com/browse/LPS-87371
[LPS-87417]: https://issues.liferay.com/browse/LPS-87417
[LPS-87419]: https://issues.liferay.com/browse/LPS-87419
[LPS-87466]: https://issues.liferay.com/browse/LPS-87466
[LPS-87469]: https://issues.liferay.com/browse/LPS-87469
[LPS-87471]: https://issues.liferay.com/browse/LPS-87471
[LPS-87479]: https://issues.liferay.com/browse/LPS-87479
[LPS-87488]: https://issues.liferay.com/browse/LPS-87488
[LPS-87503]: https://issues.liferay.com/browse/LPS-87503
[LPS-87590]: https://issues.liferay.com/browse/LPS-87590
[LPS-87776]: https://issues.liferay.com/browse/LPS-87776
[LPS-87839]: https://issues.liferay.com/browse/LPS-87839
[LPS-87890]: https://issues.liferay.com/browse/LPS-87890
[LPS-87936]: https://issues.liferay.com/browse/LPS-87936
[LPS-87978]: https://issues.liferay.com/browse/LPS-87978
[LPS-88170]: https://issues.liferay.com/browse/LPS-88170
[LPS-88171]: https://issues.liferay.com/browse/LPS-88171
[LPS-88181]: https://issues.liferay.com/browse/LPS-88181
[LPS-88183]: https://issues.liferay.com/browse/LPS-88183
[LPS-88186]: https://issues.liferay.com/browse/LPS-88186
[LPS-88223]: https://issues.liferay.com/browse/LPS-88223
[LPS-88382]: https://issues.liferay.com/browse/LPS-88382
[LPS-88524]: https://issues.liferay.com/browse/LPS-88524
[LPS-88552]: https://issues.liferay.com/browse/LPS-88552
[LPS-88645]: https://issues.liferay.com/browse/LPS-88645
[LPS-88665]: https://issues.liferay.com/browse/LPS-88665
[LPS-88784]: https://issues.liferay.com/browse/LPS-88784
[LPS-88823]: https://issues.liferay.com/browse/LPS-88823
[LPS-88851]: https://issues.liferay.com/browse/LPS-88851
[LPS-88903]: https://issues.liferay.com/browse/LPS-88903
[LPS-88909]: https://issues.liferay.com/browse/LPS-88909
[LPS-88911]: https://issues.liferay.com/browse/LPS-88911
[LPS-89126]: https://issues.liferay.com/browse/LPS-89126
[LPS-89210]: https://issues.liferay.com/browse/LPS-89210
[LPS-89228]: https://issues.liferay.com/browse/LPS-89228
[LPS-89274]: https://issues.liferay.com/browse/LPS-89274
[LPS-89369]: https://issues.liferay.com/browse/LPS-89369
[LPS-89388]: https://issues.liferay.com/browse/LPS-89388
[LPS-89415]: https://issues.liferay.com/browse/LPS-89415
[LPS-89445]: https://issues.liferay.com/browse/LPS-89445
[LPS-89456]: https://issues.liferay.com/browse/LPS-89456
[LPS-89457]: https://issues.liferay.com/browse/LPS-89457
[LPS-89518]: https://issues.liferay.com/browse/LPS-89518
[LPS-89567]: https://issues.liferay.com/browse/LPS-89567
[LPS-89568]: https://issues.liferay.com/browse/LPS-89568
[LPS-89637]: https://issues.liferay.com/browse/LPS-89637
[LPS-89874]: https://issues.liferay.com/browse/LPS-89874
[LPS-89916]: https://issues.liferay.com/browse/LPS-89916
[LPS-90204]: https://issues.liferay.com/browse/LPS-90204
[LPS-90378]: https://issues.liferay.com/browse/LPS-90378
[LPS-90380]: https://issues.liferay.com/browse/LPS-90380
[LPS-90402]: https://issues.liferay.com/browse/LPS-90402
[LPS-90523]: https://issues.liferay.com/browse/LPS-90523
[LPS-90945]: https://issues.liferay.com/browse/LPS-90945
[LPS-91222]: https://issues.liferay.com/browse/LPS-91222
[LPS-91231]: https://issues.liferay.com/browse/LPS-91231
[LPS-91241]: https://issues.liferay.com/browse/LPS-91241
[LPS-91295]: https://issues.liferay.com/browse/LPS-91295
[LPS-91342]: https://issues.liferay.com/browse/LPS-91342
[LPS-91343]: https://issues.liferay.com/browse/LPS-91343
[LPS-91378]: https://issues.liferay.com/browse/LPS-91378
[LPS-91420]: https://issues.liferay.com/browse/LPS-91420
[LPS-91463]: https://issues.liferay.com/browse/LPS-91463
[LPS-91549]: https://issues.liferay.com/browse/LPS-91549
[LPS-91772]: https://issues.liferay.com/browse/LPS-91772
[LPS-91803]: https://issues.liferay.com/browse/LPS-91803
[LPS-91846]: https://issues.liferay.com/browse/LPS-91846
[LPS-91967]: https://issues.liferay.com/browse/LPS-91967
[LPS-91970]: https://issues.liferay.com/browse/LPS-91970
[LPS-92223]: https://issues.liferay.com/browse/LPS-92223
[LPS-92311]: https://issues.liferay.com/browse/LPS-92311
[LPS-92568]: https://issues.liferay.com/browse/LPS-92568
[LPS-92677]: https://issues.liferay.com/browse/LPS-92677
[LPS-92746]: https://issues.liferay.com/browse/LPS-92746
[LPS-93045]: https://issues.liferay.com/browse/LPS-93045
[LPS-93124]: https://issues.liferay.com/browse/LPS-93124
[LPS-93258]: https://issues.liferay.com/browse/LPS-93258
[LPS-93265]: https://issues.liferay.com/browse/LPS-93265
[LPS-93350]: https://issues.liferay.com/browse/LPS-93350
[LPS-93471]: https://issues.liferay.com/browse/LPS-93471
[LPS-93483]: https://issues.liferay.com/browse/LPS-93483
[LPS-93505]: https://issues.liferay.com/browse/LPS-93505
[LPS-93506]: https://issues.liferay.com/browse/LPS-93506
[LPS-93507]: https://issues.liferay.com/browse/LPS-93507
[LPS-93510]: https://issues.liferay.com/browse/LPS-93510
[LPS-93513]: https://issues.liferay.com/browse/LPS-93513
[LPS-93707]: https://issues.liferay.com/browse/LPS-93707
[LPS-93873]: https://issues.liferay.com/browse/LPS-93873
[LPS-94033]: https://issues.liferay.com/browse/LPS-94033
[LPS-94466]: https://issues.liferay.com/browse/LPS-94466
[LPS-94523]: https://issues.liferay.com/browse/LPS-94523
[LPS-94606]: https://issues.liferay.com/browse/LPS-94606
[LPS-94764]: https://issues.liferay.com/browse/LPS-94764
[LPS-94947]: https://issues.liferay.com/browse/LPS-94947
[LPS-94948]: https://issues.liferay.com/browse/LPS-94948
[LPS-94999]: https://issues.liferay.com/browse/LPS-94999
[LPS-95079]: https://issues.liferay.com/browse/LPS-95079
[LPS-95279]: https://issues.liferay.com/browse/LPS-95279
[LPS-95330]: https://issues.liferay.com/browse/LPS-95330
[LPS-95388]: https://issues.liferay.com/browse/LPS-95388
[LPS-95413]: https://issues.liferay.com/browse/LPS-95413
[LPS-95442]: https://issues.liferay.com/browse/LPS-95442
[LPS-95455]: https://issues.liferay.com/browse/LPS-95455
[LPS-95635]: https://issues.liferay.com/browse/LPS-95635
[LPS-95723]: https://issues.liferay.com/browse/LPS-95723
[LPS-95819]: https://issues.liferay.com/browse/LPS-95819
[LPS-95915]: https://issues.liferay.com/browse/LPS-95915
[LPS-95938]: https://issues.liferay.com/browse/LPS-95938
[LPS-96018]: https://issues.liferay.com/browse/LPS-96018
[LPS-96091]: https://issues.liferay.com/browse/LPS-96091
[LPS-96095]: https://issues.liferay.com/browse/LPS-96095
[LPS-96198]: https://issues.liferay.com/browse/LPS-96198
[LPS-96206]: https://issues.liferay.com/browse/LPS-96206
[LPS-96247]: https://issues.liferay.com/browse/LPS-96247
[LPS-96252]: https://issues.liferay.com/browse/LPS-96252
[LPS-96290]: https://issues.liferay.com/browse/LPS-96290
[LPS-96376]: https://issues.liferay.com/browse/LPS-96376
[LPS-96611]: https://issues.liferay.com/browse/LPS-96611
[LPS-96830]: https://issues.liferay.com/browse/LPS-96830
[LPS-96860]: https://issues.liferay.com/browse/LPS-96860
[LPS-96911]: https://issues.liferay.com/browse/LPS-96911
[LPS-97169]: https://issues.liferay.com/browse/LPS-97169
[LPS-97550]: https://issues.liferay.com/browse/LPS-97550
[LPS-97601]: https://issues.liferay.com/browse/LPS-97601
[LPS-98022]: https://issues.liferay.com/browse/LPS-98022
[LPS-98190]: https://issues.liferay.com/browse/LPS-98190
[LPS-98417]: https://issues.liferay.com/browse/LPS-98417
[LPS-98877]: https://issues.liferay.com/browse/LPS-98877
[LPS-98879]: https://issues.liferay.com/browse/LPS-98879
[LPS-98914]: https://issues.liferay.com/browse/LPS-98914
[LPS-98937]: https://issues.liferay.com/browse/LPS-98937
[LPS-99147]: https://issues.liferay.com/browse/LPS-99147
[LPS-99308]: https://issues.liferay.com/browse/LPS-99308
[LPS-99382]: https://issues.liferay.com/browse/LPS-99382
[LPS-99442]: https://issues.liferay.com/browse/LPS-99442
[LPS-99577]: https://issues.liferay.com/browse/LPS-99577
[LPS-99977]: https://issues.liferay.com/browse/LPS-99977
[LPS-99983]: https://issues.liferay.com/browse/LPS-99983
[LPS-100153]: https://issues.liferay.com/browse/LPS-100153
[LPS-100168]: https://issues.liferay.com/browse/LPS-100168
[LPS-100335]: https://issues.liferay.com/browse/LPS-100335
[LPS-100448]: https://issues.liferay.com/browse/LPS-100448
[LPS-100515]: https://issues.liferay.com/browse/LPS-100515
[LPS-101060]: https://issues.liferay.com/browse/LPS-101060
[LPS-101089]: https://issues.liferay.com/browse/LPS-101089
[LPS-101450]: https://issues.liferay.com/browse/LPS-101450
[LPS-101463]: https://issues.liferay.com/browse/LPS-101463
[LPS-101947]: https://issues.liferay.com/browse/LPS-101947
[LPS-102243]: https://issues.liferay.com/browse/LPS-102243
[LPS-102700]: https://issues.liferay.com/browse/LPS-102700
[LPS-103051]: https://issues.liferay.com/browse/LPS-103051
[LPS-103169]: https://issues.liferay.com/browse/LPS-103169
[LPS-103466]: https://issues.liferay.com/browse/LPS-103466
[LPS-103809]: https://issues.liferay.com/browse/LPS-103809
[LPS-103937]: https://issues.liferay.com/browse/LPS-103937
[LPS-104354]: https://issues.liferay.com/browse/LPS-104354
[LPS-104355]: https://issues.liferay.com/browse/LPS-104355
[LPS-104540]: https://issues.liferay.com/browse/LPS-104540
[LPS-104646]: https://issues.liferay.com/browse/LPS-104646
[LPS-104679]: https://issues.liferay.com/browse/LPS-104679
[LPS-105280]: https://issues.liferay.com/browse/LPS-105280
[LPS-105380]: https://issues.liferay.com/browse/LPS-105380
[LPS-105502]: https://issues.liferay.com/browse/LPS-105502
[LPS-105873]: https://issues.liferay.com/browse/LPS-105873
[LPS-105889]: https://issues.liferay.com/browse/LPS-105889
[LPS-106149]: https://issues.liferay.com/browse/LPS-106149
[LPS-106167]: https://issues.liferay.com/browse/LPS-106167
[LPS-107155]: https://issues.liferay.com/browse/LPS-107155
[LPS-107202]: https://issues.liferay.com/browse/LPS-107202
[LPS-107519]: https://issues.liferay.com/browse/LPS-107519
[LPS-107612]: https://issues.liferay.com/browse/LPS-107612
[LPS-107812]: https://issues.liferay.com/browse/LPS-107812
[LPS-107862]: https://issues.liferay.com/browse/LPS-107862
[LPS-108380]: https://issues.liferay.com/browse/LPS-108380
[LPS-109312]: https://issues.liferay.com/browse/LPS-109312
[LPS-109787]: https://issues.liferay.com/browse/LPS-109787
[LPS-109820]: https://issues.liferay.com/browse/LPS-109820
[LPS-110051]: https://issues.liferay.com/browse/LPS-110051
[LPS-110131]: https://issues.liferay.com/browse/LPS-110131
[LPS-110422]: https://issues.liferay.com/browse/LPS-110422
[LPS-110835]: https://issues.liferay.com/browse/LPS-110835
[LPS-111291]: https://issues.liferay.com/browse/LPS-111291
[LPS-111460]: https://issues.liferay.com/browse/LPS-111460
[LPS-111461]: https://issues.liferay.com/browse/LPS-111461
[LPS-111700]: https://issues.liferay.com/browse/LPS-111700
[LPS-112052]: https://issues.liferay.com/browse/LPS-112052
[LPS-112922]: https://issues.liferay.com/browse/LPS-112922
[LPS-113024]: https://issues.liferay.com/browse/LPS-113024
[LPS-113180]: https://issues.liferay.com/browse/LPS-113180
[LPS-113624]: https://issues.liferay.com/browse/LPS-113624
[LPS-113914]: https://issues.liferay.com/browse/LPS-113914
[LPS-114168]: https://issues.liferay.com/browse/LPS-114168
[LPS-114565]: https://issues.liferay.com/browse/LPS-114565
[LPS-114570]: https://issues.liferay.com/browse/LPS-114570
[LPS-114705]: https://issues.liferay.com/browse/LPS-114705
[LPS-114882]: https://issues.liferay.com/browse/LPS-114882
[LPS-115015]: https://issues.liferay.com/browse/LPS-115015
[LPS-115431]: https://issues.liferay.com/browse/LPS-115431
[LPS-115714]: https://issues.liferay.com/browse/LPS-115714
[LPS-116041]: https://issues.liferay.com/browse/LPS-116041
[LPS-116049]: https://issues.liferay.com/browse/LPS-116049
[LPS-116282]: https://issues.liferay.com/browse/LPS-116282
[LPS-117314]: https://issues.liferay.com/browse/LPS-117314
[LPS-118113]: https://issues.liferay.com/browse/LPS-118113
[LPS-118868]: https://issues.liferay.com/browse/LPS-118868
[LPS-118918]: https://issues.liferay.com/browse/LPS-118918
[LPS-118936]: https://issues.liferay.com/browse/LPS-118936
[LPS-119389]: https://issues.liferay.com/browse/LPS-119389
[LPS-121480]: https://issues.liferay.com/browse/LPS-121480
[LPS-121481]: https://issues.liferay.com/browse/LPS-121481
[LPS-121824]: https://issues.liferay.com/browse/LPS-121824
[LPS-122958]: https://issues.liferay.com/browse/LPS-122958
[LPS-122967]: https://issues.liferay.com/browse/LPS-122967
[LPS-123192]: https://issues.liferay.com/browse/LPS-123192
[LPS-123871]: https://issues.liferay.com/browse/LPS-123871
[LPS-123937]: https://issues.liferay.com/browse/LPS-123937
[LPS-124014]: https://issues.liferay.com/browse/LPS-124014
[LPS-124343]: https://issues.liferay.com/browse/LPS-124343
[LPS-125578]: https://issues.liferay.com/browse/LPS-125578
[LPS-125580]: https://issues.liferay.com/browse/LPS-125580
[LPS-125632]: https://issues.liferay.com/browse/LPS-125632
[LPS-125998]: https://issues.liferay.com/browse/LPS-125998
[LPS-126919]: https://issues.liferay.com/browse/LPS-126919
[LPS-127801]: https://issues.liferay.com/browse/LPS-127801
[LPS-128116]: https://issues.liferay.com/browse/LPS-128116
[LPS-128853]: https://issues.liferay.com/browse/LPS-128853
[LPS-129267]: https://issues.liferay.com/browse/LPS-129267
[LPS-129696]: https://issues.liferay.com/browse/LPS-129696
[LPS-129874]: https://issues.liferay.com/browse/LPS-129874
[LPS-129956]: https://issues.liferay.com/browse/LPS-129956
[LPS-130505]: https://issues.liferay.com/browse/LPS-130505
[LPS-131439]: https://issues.liferay.com/browse/LPS-131439
[LPS-132042]: https://issues.liferay.com/browse/LPS-132042
[LPS-132252]: https://issues.liferay.com/browse/LPS-132252
[LPS-132730]: https://issues.liferay.com/browse/LPS-132730
[LPS-133987]: https://issues.liferay.com/browse/LPS-133987
[LPS-134301]: https://issues.liferay.com/browse/LPS-134301
[LPS-134571]: https://issues.liferay.com/browse/LPS-134571
[LPS-135076]: https://issues.liferay.com/browse/LPS-135076
[LPS-136872]: https://issues.liferay.com/browse/LPS-136872
[LPS-137358]: https://issues.liferay.com/browse/LPS-137358
[LPS-138982]: https://issues.liferay.com/browse/LPS-138982
[LPS-140947]: https://issues.liferay.com/browse/LPS-140947
[LPS-141250]: https://issues.liferay.com/browse/LPS-141250
[LPS-142100]: https://issues.liferay.com/browse/LPS-142100
[LPS-142340]: https://issues.liferay.com/browse/LPS-142340
[LPS-142398]: https://issues.liferay.com/browse/LPS-142398
[LPS-143544]: https://issues.liferay.com/browse/LPS-143544
[LPS-143577]: https://issues.liferay.com/browse/LPS-143577
[LPS-145268]: https://issues.liferay.com/browse/LPS-145268
[LPS-147105]: https://issues.liferay.com/browse/LPS-147105
[LPS-147563]: https://issues.liferay.com/browse/LPS-147563
[LPS-147656]: https://issues.liferay.com/browse/LPS-147656
[LPS-147672]: https://issues.liferay.com/browse/LPS-147672
[LPS-149188]: https://issues.liferay.com/browse/LPS-149188
[LPS-149478]: https://issues.liferay.com/browse/LPS-149478
[LPS-149555]: https://issues.liferay.com/browse/LPS-149555
[LPS-150272]: https://issues.liferay.com/browse/LPS-150272
[LPS-150312]: https://issues.liferay.com/browse/LPS-150312
[LPS-150378]: https://issues.liferay.com/browse/LPS-150378
[LPS-150769]: https://issues.liferay.com/browse/LPS-150769
[LPS-150857]: https://issues.liferay.com/browse/LPS-150857
[LPS-151340]: https://issues.liferay.com/browse/LPS-151340
[LPS-151349]: https://issues.liferay.com/browse/LPS-151349
[LPS-151626]: https://issues.liferay.com/browse/LPS-151626
[LPS-151675]: https://issues.liferay.com/browse/LPS-151675
[LPS-152789]: https://issues.liferay.com/browse/LPS-152789
[LPS-153494]: https://issues.liferay.com/browse/LPS-153494
[LPS-153667]: https://issues.liferay.com/browse/LPS-153667
[LPS-154070]: https://issues.liferay.com/browse/LPS-154070
[LPS-154072]: https://issues.liferay.com/browse/LPS-154072
[LPS-154340]: https://issues.liferay.com/browse/LPS-154340
[LPS-154671]: https://issues.liferay.com/browse/LPS-154671
[LPS-154811]: https://issues.liferay.com/browse/LPS-154811
[LPS-155204]: https://issues.liferay.com/browse/LPS-155204
[LPS-155917]: https://issues.liferay.com/browse/LPS-155917
[LPS-156068]: https://issues.liferay.com/browse/LPS-156068
[LPS-157179]: https://issues.liferay.com/browse/LPS-157179
[LPS-158124]: https://issues.liferay.com/browse/LPS-158124
[LPS-159495]: https://issues.liferay.com/browse/LPS-159495
[LPS-160184]: https://issues.liferay.com/browse/LPS-160184
[LPS-161202]: https://issues.liferay.com/browse/LPS-161202
[LPS-162929]: https://issues.liferay.com/browse/LPS-162929
[LPS-163589]: https://issues.liferay.com/browse/LPS-163589
[LPS-164101]: https://issues.liferay.com/browse/LPS-164101
[LPS-166111]: https://issues.liferay.com/browse/LPS-166111
[LPS-166479]: https://issues.liferay.com/browse/LPS-166479
[LPS-166481]: https://issues.liferay.com/browse/LPS-166481
[LPS-167024]: https://issues.liferay.com/browse/LPS-167024
[LPS-167335]: https://issues.liferay.com/browse/LPS-167335
[LPS-167694]: https://issues.liferay.com/browse/LPS-167694
[LPS-168892]: https://issues.liferay.com/browse/LPS-168892
[LPS-168901]: https://issues.liferay.com/browse/LPS-168901
[LPS-168980]: https://issues.liferay.com/browse/LPS-168980
[LPS-169432]: https://issues.liferay.com/browse/LPS-169432
[LPS-169543]: https://issues.liferay.com/browse/LPS-169543
[LPS-169846]: https://issues.liferay.com/browse/LPS-169846
[LPS-170503]: https://issues.liferay.com/browse/LPS-170503
[LPS-171941]: https://issues.liferay.com/browse/LPS-171941
[LPS-172305]: https://issues.liferay.com/browse/LPS-172305
[LPS-172374]: https://issues.liferay.com/browse/LPS-172374
[LPS-172665]: https://issues.liferay.com/browse/LPS-172665
[LPS-172781]: https://issues.liferay.com/browse/LPS-172781
[LPS-172794]: https://issues.liferay.com/browse/LPS-172794
[LPS-172813]: https://issues.liferay.com/browse/LPS-172813
[LPS-172916]: https://issues.liferay.com/browse/LPS-172916
[LPS-172978]: https://issues.liferay.com/browse/LPS-172978
[LPS-173583]: https://issues.liferay.com/browse/LPS-173583
[LPS-173718]: https://issues.liferay.com/browse/LPS-173718
[LPS-173869]: https://issues.liferay.com/browse/LPS-173869
[LPS-174140]: https://issues.liferay.com/browse/LPS-174140
[LPS-174535]: https://issues.liferay.com/browse/LPS-174535
[LPS-175159]: https://issues.liferay.com/browse/LPS-175159
[LPS-175188]: https://issues.liferay.com/browse/LPS-175188
[LPS-175300]: https://issues.liferay.com/browse/LPS-175300
[LPS-175968]: https://issues.liferay.com/browse/LPS-175968
[LPS-176090]: https://issues.liferay.com/browse/LPS-176090
[LPS-176097]: https://issues.liferay.com/browse/LPS-176097
[LPS-176175]: https://issues.liferay.com/browse/LPS-176175
[LPS-177026]: https://issues.liferay.com/browse/LPS-177026
[LPS-177027]: https://issues.liferay.com/browse/LPS-177027
[LPS-177352]: https://issues.liferay.com/browse/LPS-177352
[LPS-177378]: https://issues.liferay.com/browse/LPS-177378
[LPS-177393]: https://issues.liferay.com/browse/LPS-177393
[LPS-177812]: https://issues.liferay.com/browse/LPS-177812
[LPS-178596]: https://issues.liferay.com/browse/LPS-178596
[LPS-178903]: https://issues.liferay.com/browse/LPS-178903
[LPS-178925]: https://issues.liferay.com/browse/LPS-178925
[LPS-179095]: https://issues.liferay.com/browse/LPS-179095
[LPS-179114]: https://issues.liferay.com/browse/LPS-179114
[LPS-179132]: https://issues.liferay.com/browse/LPS-179132
[LPS-179303]: https://issues.liferay.com/browse/LPS-179303
[LPS-179775]: https://issues.liferay.com/browse/LPS-179775
[LPS-179794]: https://issues.liferay.com/browse/LPS-179794
[LPS-179796]: https://issues.liferay.com/browse/LPS-179796
[LPS-179816]: https://issues.liferay.com/browse/LPS-179816
[LPS-179838]: https://issues.liferay.com/browse/LPS-179838
[LPS-180050]: https://issues.liferay.com/browse/LPS-180050
[LPS-180091]: https://issues.liferay.com/browse/LPS-180091
[LPS-180402]: https://issues.liferay.com/browse/LPS-180402
[LPS-180500]: https://issues.liferay.com/browse/LPS-180500
[LPS-180505]: https://issues.liferay.com/browse/LPS-180505
[LPS-180622]: https://issues.liferay.com/browse/LPS-180622
[LPS-180905]: https://issues.liferay.com/browse/LPS-180905
[LPS-181002]: https://issues.liferay.com/browse/LPS-181002
[LPS-181118]: https://issues.liferay.com/browse/LPS-181118
[LPS-181195]: https://issues.liferay.com/browse/LPS-181195
[LPS-181200]: https://issues.liferay.com/browse/LPS-181200
[LPS-181208]: https://issues.liferay.com/browse/LPS-181208
[LPS-181216]: https://issues.liferay.com/browse/LPS-181216
[LPS-181266]: https://issues.liferay.com/browse/LPS-181266
[LPS-181303]: https://issues.liferay.com/browse/LPS-181303
[LPS-181331]: https://issues.liferay.com/browse/LPS-181331
[LPS-181335]: https://issues.liferay.com/browse/LPS-181335
[LPS-181454]: https://issues.liferay.com/browse/LPS-181454
[LPS-181508]: https://issues.liferay.com/browse/LPS-181508
[LPS-181738]: https://issues.liferay.com/browse/LPS-181738
[LPS-182065]: https://issues.liferay.com/browse/LPS-182065
[LPS-182153]: https://issues.liferay.com/browse/LPS-182153
[LPS-182253]: https://issues.liferay.com/browse/LPS-182253
[LPS-182359]: https://issues.liferay.com/browse/LPS-182359
[LPS-182369]: https://issues.liferay.com/browse/LPS-182369
[LPS-182410]: https://issues.liferay.com/browse/LPS-182410
[LPS-182759]: https://issues.liferay.com/browse/LPS-182759
[LPS-182799]: https://issues.liferay.com/browse/LPS-182799
[LPS-183167]: https://issues.liferay.com/browse/LPS-183167
[LPS-183262]: https://issues.liferay.com/browse/LPS-183262
[LPS-183286]: https://issues.liferay.com/browse/LPS-183286
[LPS-183439]: https://issues.liferay.com/browse/LPS-183439
[LPS-183588]: https://issues.liferay.com/browse/LPS-183588
[LPS-183973]: https://issues.liferay.com/browse/LPS-183973
[LPS-184016]: https://issues.liferay.com/browse/LPS-184016
[LPS-184062]: https://issues.liferay.com/browse/LPS-184062
[LPS-184465]: https://issues.liferay.com/browse/LPS-184465
[LPS-185020]: https://issues.liferay.com/browse/LPS-185020
[LPS-185460]: https://issues.liferay.com/browse/LPS-185460
[LPS-185695]: https://issues.liferay.com/browse/LPS-185695
[LPS-186060]: https://issues.liferay.com/browse/LPS-186060
[LPS-186063]: https://issues.liferay.com/browse/LPS-186063
[LPS-186127]: https://issues.liferay.com/browse/LPS-186127
[LPS-186211]: https://issues.liferay.com/browse/LPS-186211
[LPS-186327]: https://issues.liferay.com/browse/LPS-186327
[LPS-187069]: https://issues.liferay.com/browse/LPS-187069
[LPS-187428]: https://issues.liferay.com/browse/LPS-187428
[LPS-187692]: https://issues.liferay.com/browse/LPS-187692
[LPS-188038]: https://issues.liferay.com/browse/LPS-188038
[LPS-188465]: https://issues.liferay.com/browse/LPS-188465
[LPS-188564]: https://issues.liferay.com/browse/LPS-188564
[LPS-188758]: https://issues.liferay.com/browse/LPS-188758
[LPS-188912]: https://issues.liferay.com/browse/LPS-188912
[LPS-189252]: https://issues.liferay.com/browse/LPS-189252
[LPS-190136]: https://issues.liferay.com/browse/LPS-190136
[LPS-190887]: https://issues.liferay.com/browse/LPS-190887
[LPS-190915]: https://issues.liferay.com/browse/LPS-190915
[LPS-191500]: https://issues.liferay.com/browse/LPS-191500
[LPS-192082]: https://issues.liferay.com/browse/LPS-192082
[LPS-192413]: https://issues.liferay.com/browse/LPS-192413
[LPS-193473]: https://issues.liferay.com/browse/LPS-193473
[LPS-193507]: https://issues.liferay.com/browse/LPS-193507
[LPS-193534]: https://issues.liferay.com/browse/LPS-193534
[LPS-193777]: https://issues.liferay.com/browse/LPS-193777
[LPS-193787]: https://issues.liferay.com/browse/LPS-193787
[LPS-194134]: https://issues.liferay.com/browse/LPS-194134
[LPS-194218]: https://issues.liferay.com/browse/LPS-194218
[LPS-194818]: https://issues.liferay.com/browse/LPS-194818
[LPS-195170]: https://issues.liferay.com/browse/LPS-195170
[LPS-195564]: https://issues.liferay.com/browse/LPS-195564
[LPS-195925]: https://issues.liferay.com/browse/LPS-195925
[LPS-196218]: https://issues.liferay.com/browse/LPS-196218
[LPS-196455]: https://issues.liferay.com/browse/LPS-196455
[LPS-196539]: https://issues.liferay.com/browse/LPS-196539
[LPS-196617]: https://issues.liferay.com/browse/LPS-196617
[LPS-197368]: https://issues.liferay.com/browse/LPS-197368
[LPS-197582]: https://issues.liferay.com/browse/LPS-197582
[LPS-197623]: https://issues.liferay.com/browse/LPS-197623
[LPS-197901]: https://issues.liferay.com/browse/LPS-197901
[LPS-198717]: https://issues.liferay.com/browse/LPS-198717
[LPS-198875]: https://issues.liferay.com/browse/LPS-198875
[LPS-199271]: https://issues.liferay.com/browse/LPS-199271
[LPS-199324]: https://issues.liferay.com/browse/LPS-199324
[LPS-199421]: https://issues.liferay.com/browse/LPS-199421
[LPS-199457]: https://issues.liferay.com/browse/LPS-199457
[LPS-199563]: https://issues.liferay.com/browse/LPS-199563
[LPS-199623]: https://issues.liferay.com/browse/LPS-199623
[LPS-200165]: https://issues.liferay.com/browse/LPS-200165
[LPS-200190]: https://issues.liferay.com/browse/LPS-200190
[LPS-200543]: https://issues.liferay.com/browse/LPS-200543
[LPS-200552]: https://issues.liferay.com/browse/LPS-200552
[LPS-200966]: https://issues.liferay.com/browse/LPS-200966
[LPS-201781]: https://issues.liferay.com/browse/LPS-201781
[LPS-201858]: https://issues.liferay.com/browse/LPS-201858
[LPS-202307]: https://issues.liferay.com/browse/LPS-202307
[LPS-202509]: https://issues.liferay.com/browse/LPS-202509
[LPS-202874]: https://issues.liferay.com/browse/LPS-202874
[LPS-203017]: https://issues.liferay.com/browse/LPS-203017
[LPS-203145]: https://issues.liferay.com/browse/LPS-203145
[LPS-203434]: https://issues.liferay.com/browse/LPS-203434
[LPS-203507]: https://issues.liferay.com/browse/LPS-203507
[LPS-203804]: https://issues.liferay.com/browse/LPS-203804
[LPS-203834]: https://issues.liferay.com/browse/LPS-203834
[LPS-203981]: https://issues.liferay.com/browse/LPS-203981
[LPS-204321]: https://issues.liferay.com/browse/LPS-204321
[LPS-204423]: https://issues.liferay.com/browse/LPS-204423
[LPS-204449]: https://issues.liferay.com/browse/LPS-204449
[LPS-204572]: https://issues.liferay.com/browse/LPS-204572
[LPS-204924]: https://issues.liferay.com/browse/LPS-204924
[LPS-204953]: https://issues.liferay.com/browse/LPS-204953
[LPS-204967]: https://issues.liferay.com/browse/LPS-204967
[LPS-205214]: https://issues.liferay.com/browse/LPS-205214
[LPS-205476]: https://issues.liferay.com/browse/LPS-205476
[LPS-205913]: https://issues.liferay.com/browse/LPS-205913
[LPS-206195]: https://issues.liferay.com/browse/LPS-206195
[LPS-206269]: https://issues.liferay.com/browse/LPS-206269
[LPS-206278]: https://issues.liferay.com/browse/LPS-206278
[LPS-206396]: https://issues.liferay.com/browse/LPS-206396
[LRCI-65]: https://issues.liferay.com/browse/LRCI-65
[LRCI-264]: https://issues.liferay.com/browse/LRCI-264
[LRCI-350]: https://issues.liferay.com/browse/LRCI-350
[LRCI-3496]: https://issues.liferay.com/browse/LRCI-3496
[LRCI-4454]: https://issues.liferay.com/browse/LRCI-4454
[LRDOCS-2547]: https://issues.liferay.com/browse/LRDOCS-2547
[LRDOCS-2594]: https://issues.liferay.com/browse/LRDOCS-2594
[LRDOCS-2841]: https://issues.liferay.com/browse/LRDOCS-2841
[LRDOCS-3023]: https://issues.liferay.com/browse/LRDOCS-3023
[LRDOCS-3038]: https://issues.liferay.com/browse/LRDOCS-3038
[LRDOCS-4111]: https://issues.liferay.com/browse/LRDOCS-4111
[LRDOCS-6300]: https://issues.liferay.com/browse/LRDOCS-6300
[LRDOCS-6412]: https://issues.liferay.com/browse/LRDOCS-6412
[LRDOCS-8120]: https://issues.liferay.com/browse/LRDOCS-8120
[LRQA-25540]: https://issues.liferay.com/browse/LRQA-25540
[LRQA-25824]: https://issues.liferay.com/browse/LRQA-25824
[LRQA-45313]: https://issues.liferay.com/browse/LRQA-45313
[LRQA-46630]: https://issues.liferay.com/browse/LRQA-46630
[LRQA-46662]: https://issues.liferay.com/browse/LRQA-46662
[LRQA-47104]: https://issues.liferay.com/browse/LRQA-47104
[POSHI-115]: https://issues.liferay.com/browse/POSHI-115
[POSHI-232]: https://issues.liferay.com/browse/POSHI-232
[POSHI-251]: https://issues.liferay.com/browse/POSHI-251
[POSHI-261]: https://issues.liferay.com/browse/POSHI-261
[POSHI-324]: https://issues.liferay.com/browse/POSHI-324
[POSHI-353]: https://issues.liferay.com/browse/POSHI-353
[POSHI-362]: https://issues.liferay.com/browse/POSHI-362
[POSHI-373]: https://issues.liferay.com/browse/POSHI-373
[POSHI-426]: https://issues.liferay.com/browse/POSHI-426
[POSHI-432]: https://issues.liferay.com/browse/POSHI-432
[POSHI-438]: https://issues.liferay.com/browse/POSHI-438
[POSHI-469]: https://issues.liferay.com/browse/POSHI-469
[POSHI-471]: https://issues.liferay.com/browse/POSHI-471
[POSHI-474]: https://issues.liferay.com/browse/POSHI-474
[POSHI-486]: https://issues.liferay.com/browse/POSHI-486
[POSHI-503]: https://issues.liferay.com/browse/POSHI-503
[POSHI-517]: https://issues.liferay.com/browse/POSHI-517
[POSHI-522]: https://issues.liferay.com/browse/POSHI-522
[POSHI-528]: https://issues.liferay.com/browse/POSHI-528
[POSHI-543]: https://issues.liferay.com/browse/POSHI-543
[POSHI-557]: https://issues.liferay.com/browse/POSHI-557
[POSHI-570]: https://issues.liferay.com/browse/POSHI-570
[POSHI-587]: https://issues.liferay.com/browse/POSHI-587
[POSHI-599]: https://issues.liferay.com/browse/POSHI-599
[POSHI-604]: https://issues.liferay.com/browse/POSHI-604
[POSHI-610]: https://issues.liferay.com/browse/POSHI-610
[POSHI-637]: https://issues.liferay.com/browse/POSHI-637
[POSHI-645]: https://issues.liferay.com/browse/POSHI-645
[POSHI-659]: https://issues.liferay.com/browse/POSHI-659
[POSHI-686]: https://issues.liferay.com/browse/POSHI-686
[POSHI-688]: https://issues.liferay.com/browse/POSHI-688
[RELEASE-1607]: https://issues.liferay.com/browse/RELEASE-1607
[SHA-512]: https://issues.liferay.com/browse/SHA-512