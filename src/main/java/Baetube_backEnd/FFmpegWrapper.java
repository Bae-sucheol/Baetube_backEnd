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
	private static final String basePath = Paths.get("G:", "baetube", "video").toString();
	//private static final String prefix = ".mp4";
	private static final String prefix = ".m3u8";
	private static FFmpeg ffmpeg;
	private static FFprobe ffprobe;
	private static final int resolutions[][] = {{1920, 1080}, {1280, 720}, {854, 480}};
	private static final long bitrates[] = {6000L, 4500L, 3000L};
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
		ffmpeg = new FFmpeg(ffmpegPath);
		ffprobe = new FFprobe(ffprobePath);
		FFmpegProbeResult probeResult = ffprobe.probe(filePath);
		FFmpegStream stream = probeResult.getStreams().get(0);
		
		long sourceBitrate = stream.bit_rate;
		//int width = stream.width;
		int height = stream.height;
		
		String fileBasePath = Paths.get(basePath, fileName).toString();
		
		File outputFile = new File(fileBasePath);
		
		if(!outputFile.exists())
		{
			outputFile.mkdirs();
		}
		
		List<FFmpegBuilder> builderList = makeBuilders(probeResult, fileName, fileBasePath, height, sourceBitrate);
		
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
	
	private List<FFmpegBuilder> makeBuilders(FFmpegProbeResult probeResult, String fileName, String fileBasePath, int height, long sourceBitrate)
	{
		ArrayList<FFmpegBuilder> builderList = new ArrayList<>();
		
		for (int i = 0; i < 3; i++)
		{
			int resolutionWidth = resolutions[i][0];
			int resolutionHeight = resolutions[i][1];
			
			if(resolutionHeight <= height)
			{
				String fileBaseResolutionPath = Paths.get(fileBasePath, String.valueOf(resolutionHeight)).toString();
				
				File baseResolutionFile = new File(fileBaseResolutionPath);
				
				if(!baseResolutionFile.exists())
				{
					baseResolutionFile.mkdirs();
				}
				
				//String fileNamePrefix = fileName + "_" + prefix;
				//String fileNamePrefix = fileName + "_" + String.valueOf(resolutionHeight) + "_" + prefix;
				String fileNamePrefix = fileName + prefix;
				String destinationPath = Paths.get(fileBaseResolutionPath, fileNamePrefix).toString();
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
						.addOutput(destinationPath)
						.disableSubtitle()
						.setAudioChannels(2)
						.setVideoResolution(resolutionWidth, resolutionHeight)
						.setVideoBitRate(Math.min(sourceBitrate , bitrates[i] * bitrateUnit))
						.setStrict(FFmpegBuilder.Strict.EXPERIMENTAL)
						.addExtraArgs("-threads", "4")
						.addExtraArgs("-profile:v", "baseline")
	        			.addExtraArgs("-level:v", "4.0")
	        			.addExtraArgs("-f", "hls")
	        			.addExtraArgs("-start_number", "0")
	        			.addExtraArgs("-hls_time", "2")
	        			.addExtraArgs("-hls_list_size", "0");
				
			
				FFmpegBuilder ffmpegBuilder = ffmpegOutputBuilder.done();
					
				
				/*
				FFmpegBuilder ffmpegBuilder = new FFmpegBuilder()
						.addExtraArgs("-threads", "4")
						.addExtraArgs("-profile:v", "baseline")
	        			.addExtraArgs("-level:v", "4.0")
	        			.addExtraArgs("-map", "0:v:0")
	        			.addExtraArgs("-map", "0:a:0")
	        			.addExtraArgs("-filter:v:0", "scale=w=480:h=360")
	        			.addExtraArgs("-maxrate:v:0", "600k")
	        			.addExtraArgs("-b:a:0", "64k")
	        			.addExtraArgs("-var_stream_map", "v:0,a:0,name:360p")
	        			.addExtraArgs("-start_number", "0")
	        			.addExtraArgs("-hls_time", "2")
	        			.addExtraArgs("-hls_list_size", "0")
	        			.addExtraArgs("-f", "hls")
	        			.addExtraArgs("-hls_flags", "independent_segments")
	        			.addExtraArgs("-master_pl_name",  "livestream.m3u8")
	        			.addExtraArgs("-y", "livestream-%v.m3u8")
	        			//.addExtraArgs("-master_pl_name", destinationPath)
	        			//.addExtraArgs("-y", Paths.get(fileBaseResolutionPath, fileName + "-%v" + prefix).toString())
						.addInput(probeResult)
						.addOutput(destinationPath)
						.done();
				*/
			
				
				builderList.add(ffmpegBuilder);
				
			}
		}
		
		return builderList;
	}

}
