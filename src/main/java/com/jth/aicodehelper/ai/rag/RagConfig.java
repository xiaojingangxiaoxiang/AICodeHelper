package com.jth.aicodehelper.ai.rag;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 加载RAG
 */
@Configuration
public class RagConfig {
    @Resource
    private EmbeddingModel qwenEmbeddingModel;

    @Resource
    private EmbeddingStore<TextSegment> qwenTextSegmentStore;

    @Bean
    public ContentRetriever contentRetriever() {
        // RAG
        // 加载文档
        List<Document> documents = FileSystemDocumentLoader.loadDocuments("src/main/resources/docs");
        // 文档切割 最大字符串和重叠字符串
        DocumentByParagraphSplitter documentByParagraphSplitter = new DocumentByParagraphSplitter(1000, 200);
        // 自定义文档加载器，把文档转化为向量并保存到向量数据库
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .documentSplitter(documentByParagraphSplitter)
                .textSegmentTransformer(textSegment ->
                        TextSegment.from(textSegment.metadata().getString("file_name") + "\n" + textSegment.text()
                        , textSegment.metadata()))
                .embeddingModel(qwenEmbeddingModel)
                .embeddingStore(qwenTextSegmentStore)
                .build();
        // 加载文档
        ingestor.ingest(documents);
        // 自定义内容加载器
        return EmbeddingStoreContentRetriever.builder()
                .embeddingStore(qwenTextSegmentStore)
                .embeddingModel(qwenEmbeddingModel)
                .maxResults(5) // 最多5条结果
                .minScore(0.75) // 过滤掉分数小于0.75的结果
                .build();
    }
}
