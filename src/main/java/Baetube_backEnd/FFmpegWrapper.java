package Baetube_backEnd;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.builder.FFmpegOutputBuilder;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.probe.FFmpegStream;


public class FFmpegWrapper
{
	private static final String ffmpegPath = Paths.get("G:", "baetube", "ffmpeg", "bin", "ffmpeg").toString();
	private static final String ffprobePath = Paths.get("G:", "baetube", "ffmpeg", "bin", "ffprobe").toString();
	private static final String basePath = Paths.get("G:", "baetube", "temp").toString();
	//private static final String prefix = ".mp4";
	private static final String prefix = ".m3u8";
	private static FFmpeg ffmpeg;
	private static FFprobe ffprobe;
	private static final int resolutions[][] = {{1920, 1080}, {1280, 720}, {854, 480}};
	private static final long bitrates[] = {12000L, 7500L, 4000L};
	private static final long bitrateUnit = 1000L;
	
	public FFmpegWrapper()
	{
	}
	
	/**
	 * ffmpeg 사전 준비 작업 메소드
	 * @throws IOException
	 */
	public void convert(String filePath, String fileName) throws IOException
	{
		/*
		 * 싱글톤으로 만드는게 좋을 것 같다. 테스트 후 리펙토링 한다.
		 */
		ffmpeg = new FFmpeg(ffmpegPath);
		ffprobe = new FFprobe(ffprobePath);
		FFmpegProbeResult probeResult = ffprobe.probe(filePath);
		FFmpegStream stream = probeResult.getStreams().get(0);
		
		long sourceBitrate = stream.bit_rate;
		int width = stream.width;
		int height = stream.height;
		
		File outputFile = new File(basePath);
		
		if(!outputFile.exists())
		{
			outputFile.mkdirs();
		}
		
		List<FFmpegBuilder> builderList = makeBuilders(probeResult, fileName, height, sourceBitrate);
		
		FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
		
		/*
		 * executor에 버퍼가 없어 하나씩 실행해야 한다.
		 * 능력이 된다면 비동기로 처리하는게 좋을 것 같다.
		 */
		
		for (FFmpegBuilder builder : builderList)
		{
			executor.createJob(builder).run();
		}
		
	}
	
	private List<FFmpegBuilder> makeBuilders(FFmpegProbeResult probeResult, String fileName, int height, long sourceBitrate)
	{
		ArrayList<FFmpegBuilder> builderList = new ArrayList<>();
		
		for (int i = 0; i < 1; i++)
		{
			int resolutionWidth = resolutions[i][0];
			int resolutionHeight = resolutions[i][1];
			
			if(resolutionHeight <= height)
			{
				String fileNamePrefix = fileName + "_" + String.valueOf(resolutionHeight) + prefix;
				String destinationPath = Paths.get(basePath, fileNamePrefix).toString();
				/*
				FFmpegBuilder ffmpegBuilder = new FFmpegBuilder()
						.setInput(probeResult)
						.overrideOutputFiles(true)
						.addOutput(destinationPath)
						.setFormat("mp4")
						.setVideoCodec("h264")
						.disableSubtitle()
						.setAudioChannels(2)
						.setVideoResolution(resolutionWidth, resolutionHeight)
						.setVideoBitrate(bitrates[i] * bitrateUnit)
						.setStrict(FFmpegBuilder.Strict.EXPERIMENTAL)
						.done();
				*/

				
				FFmpegOutputBuilder ffmpegOutputBuilder = new FFmpegBuilder()
						.setInput(probeResult)
						.overrideOutputFiles(true)
						.addOutput(destinationPath);
						
				FFmpegBuilder ffmpegBuilder = ffmpegOutputBuilder
						.disableSubtitle()
						.setAudioChannels(2)
						.setVideoResolution(resolutionWidth, resolutionHeight)
						.setVideoBitRate(Math.min(sourceBitrate , bitrates[i] * bitrateUnit))
						.setStrict(FFmpegBuilder.Strict.EXPERIMENTAL)
						.addExtraArgs("-profile:v", "baseline")
	        			.addExtraArgs("-level:v", "4.0")
	        			.addExtraArgs("-start_number", "0")
	        			.addExtraArgs("-hls_time", "2")
	        			.addExtraArgs("-hls_list_size", "0")
	        			.addExtraArgs("-f", "hls")
	        			.addExtraArgs("-threads", "4")
						.done();
				
				builderList.add(ffmpegBuilder);
			}
		}
		
		return builderList;
	}

}
